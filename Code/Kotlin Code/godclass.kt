import java.time.LocalDate
import java.time.Period

interface BorrowableItem{
    fun uniqueItemId(): Int
    fun commonName(): String
    fun borrowPeriod(): Int
}

class Page (
    private val sectionHeader: String,
    private val body: String
)

class Book (
    private val bookId: Int,
    private val title: String,
    private val author: String,
    private val publishDate: LocalDate,
    private val pages: List<Page>
): BorrowableItem {

    public fun coverInfo(): String {
        return "Title: $title\nAuthor: $author"
    }

    override fun uniqueItemId(): Int {
        return bookId
    }

    override fun commonName(): String {
        return "Book: $title by $author"
    }

    override fun borrowPeriod(): Int = 7

}

class LibraryCard (
    private val idNumber: Int,
    private val name: String,
    private val borrowedItems: MutableMap<BorrowableItem, LocalDate>
) {

    public fun borrowItem(item: BorrowableItem, date: LocalDate) {
        borrowedItems[item] = date
    }

    public fun borrowerReport(): String {
        var r = "$name\n"
        for ((item, date) in borrowedItems) {
            r += "${item.commonName()}, borrow date: $date\n"
        }
        return r
    }

    public fun returnItem(b: BorrowableItem) {
        borrowedItems.remove(b)
    }

    private fun penalty(b: BorrowableItem, today: LocalDate) : Float {
        val borrowDate = borrowedItems[b] ?: today
        val dueDate: LocalDate = when {
            b is Book -> borrowDate.plusDays(7)
            b is Periodical -> borrowDate.plusDays(1)
            b is PC -> borrowDate.plusDays(0)
            else -> borrowDate.plusDays(0)
        }
        val daysDiff = Period.between(dueDate, today).days.toFloat()
        return (if (daysDiff < 0) 0f else daysDiff) * 3.5f
    }

    public fun itemsDue(today: LocalDate): List<BorrowableItem> {
        val itemList: MutableList<BorrowableItem> = mutableListOf()
        for ((item, date) in borrowedItems) 
            if (today >= date.plusDays(item.borrowPeriod().toLong()))
                itemList.add(item)

        return itemList.toList()
    }

    public fun totalPenalty(today: LocalDate): Float{
        var cumulativePenalty = 0f
        for (dueItem in itemsDue(today))
            cumulativePenalty += penalty(dueItem, today)

        return cumulativePenalty
    }

}

class Periodical (
    private val periodicalId: Int,
    private val title: String,
    private val issue: LocalDate,
    private val pages: List<Page>
): BorrowableItem {

    override fun commonName(): String = "$title issue: $issue"
    override fun uniqueItemId(): Int = periodicalId
    override fun borrowPeriod(): Int = 1

}

class PC (
    private val pcID: Int,
): BorrowableItem {

    override fun commonName(): String = "PC$pcID"
    override fun uniqueItemId(): Int = pcID
    override fun borrowPeriod(): Int = 0

}

fun main() {
    val card = LibraryCard(1, "Rubelito Abella", mutableMapOf())
    val book = Book(
        15, 
        "Necronomicon", 
        "Hermes Trismegistus",
        LocalDate.of(1800, 12, 12),
        listOf()
    )
    val pc = PC(3)

    card.borrowItem(book, LocalDate.of(2025,10,10))
    card.borrowItem(pc, LocalDate.of(2025,10,15))

    println(card.borrowerReport())
    println("total penalty: ${card.totalPenalty(LocalDate.of(2025, 10, 20))}")
}
