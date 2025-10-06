# Lab Exercise 6 (Borrowing from the Library)

## Task

You are to implement the following system. This system represents the borrowing and returning functions of a library. Here's the class diagram. **Edit the file that came with this document.** The edited file is what you are going to submit.

![UML](https://i.imgur.com/9zHBavi.png)

Here is the available code:

```kotlin
package booksBorrowing.requestedFiles

import java.time.*
import kotlin.collections.iterator

/**
 * Something from the library that can be borrowed
 *
 */
interface BorrowableItem{
    /**
     * @return unique identifier for the specific BorrowableItem instance
     */
    fun uniqueItemId(): Int
    /**
     * @return the common name for the specific BorrowableItem instance
     */
    fun commonName(): String
}

/**
 * this class simply holds page information of a Book
 */
class Page (
    private val sectionHeader: String,
    private val body: String
)


/**
 * A [BorrowableItem] realization that represents a book from a library
 * @property bookId the unique identifier for the book, returned by [uniqueItemId]
 * @property title the title of the book used alongside [author] for [coverInfo]
 * @property author the author of the book used alongside [title] for [coverInfo]
 * @property publishDate the date this book was published
 * @property pages a list of [Page]s represents the books contents
 */
class Book (
    private val bookId: Int,
    private val title: String,
    private val author: String,
    private val publishDate: LocalDate,
    private val pages: List<Page>
): BorrowableItem {

    /**
     * Information found at the cover of the book
     */
    public fun coverInfo(): String {
        return "$title by $author"
    }

    /**
     * Unique identifier for this book instance
     * @return [bookId]
     */
    override fun uniqueItemId(): Int {
        return bookId
    }

    /**
     * Common name for this book instance
     * @return [coverInfo]
     */
    override fun commonName(): String {
        return commonName()
    }


}

/**
 * Represents a Library Card. Contains borrower information
 * @property idNumber unique identifier for the borrower
 * @property name name of the borrower
 * @property borrowedItems a mutable map containing the borrowed items mapped to when said items were borrowed
 */
class LibraryCard (
    private val idNumber: Int,
    private val name: String,
    private val borrowedItems: MutableMap<BorrowableItem, LocalDate>
) {

    /**
     * This method is used if a user borrows an item from the library.
     * @param item the [BorrowableItem] instance borrowed,
     * @param date the date this instance is borrowed
     * When invoked [borrowedItems] gains a new entry corresponding to the passed [item] and [date]
     */
    public fun borrowItem(item: BorrowableItem, date: LocalDate) {
        borrowedItems[item] = date
    }

    /**
     * This method is used to generate the borrower information.
     * it returns the borrower [name] along with a list of borrowed items and when the items were borrowed
     */
    public fun borrowerReport(): String {
        var r = "$name\n"
        for ((item, date) in borrowedItems) {
            r += "${item.commonName()}, borrow date: $date"
        }
        return r
    }
}
```



## What you should do:

#### The class definitions above are still missing `Periodical` and `PC`. 
 - a **`Periodical`** represents a periodical (newspaper, magazines, etc). It is a realization of a `BorrowableItem`. It contains the following methods and attributes:
      - `__init__`: initializes a periodical instance with the attributes `__periodicalID:int`, `__title:str`, `__issue:date`, `__pages:list[Page]`
     - `__periodicalID:int`: unique id for a periodical
     - `__title:str`: The title of the periodical ("National Geographic", "New York Times")
     - `__issue:date`: The date when the issue was published
     - `__pages:list[Page]`: A list of `Page`s that represent the contents
     - `uniqueItemId()`: Returns `periodicalID`
     - `commonName():str`: (Implementation of the abstract method from `BorrowableItem`). It returns the title and the issue date in year-month-day format as a string for example "National Geographic issue: 2001-4-6")
 - a **`PC`** represents a library PC. It is a realization of a `BorrowableItem`.  It contains the following methods and attributes:
      - `__init__`: initializes a PC instance with the attribute `__pcID:int`.
     - `__pcID:int` : unique id for a PC
     - `uniqueItemId():int`: Returns `__pcID`
     - `commonName():str`: (Implementation of the abstract method from `BorrowableItem`). It returns "PC<_\_pcID>" (the string "PC" followed by the value attribute `__pcID`, for example "PC1342")

#### Add the following methods to `LibraryCard` and implement them
 - **`returnItem(b:BorrowableItem):`** : returns nothing, it removes the `BorrowableItem`, `b` from the `__borrowedItems` dictionary.
 - **`penalty(b:BorrowableItem,today:date):float`** : returns a float which is the calculated penalty for `BorrowableItem`, `b` when returned today. Every day after the due date the penalty increases by 3.5. An item which is overdue for 4 days has a penalty of 14. 
 - **`itemsDue(today:date):list[BorrowableItem]`** : returns a list of `BorrowableItem`s which are on or past the due date. The due date for a `Book` is 7 days, a `Periodical` is 1 day, and a `PC` is 0 days.
 - **`totalPenalty(today:date):float`** : returns a float which is the total penalty for all the overdue items when calculated today.

> The parameter `today:date` represents the date today. For example `itemsDue(today:date)` will return a list of `BorrowableItem`s past the due date if if the date was `today`.

Feel free (in fact you are encouraged) to add extra methods to any of the classes above that will help you in implementing the whole system. Just make sure the extra methods don't unnecessarily expose hidden attributes.

### Self-testing
Test your code using the included `tests.py` file. First make sure that the file name of the python script containing your system is "`lab6.py`". Make sure that both `tests.py` and `lab6.py` are on the same library. The aforementioned steps are necessary so that `tests.py` successfully imports the classes and functions of `lab6.py`.

Run `tests.py` using the command `python tests.py`. Running `tests.py` on your IDE should also work. If your code passes the test cases, you should find the following results:
```
> python tests.py
```
```
polymorphism test:
OK
returning items test:
OK
penalty test:
OK
total penalty test:
OK
```

## Assessment Criteria

- Completeness of `BorrowableItems` attributes and methods - 20
- Completeness of `LibraryCard` methods - 20
- Correctness of attribute and methods names - 10