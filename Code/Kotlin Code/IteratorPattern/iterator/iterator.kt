package iterator

interface Iterator<T> {
    fun next(): T
    fun hasNext(): Boolean
}

class ListIterator<T>(val traversedList: MyList<T>) : Iterator<T> {
    var currentIndex = 0
    override fun next(): T {
        currentIndex += 1
        return traversedList.elementAtIndex(currentIndex - 1)
    }
    override fun hasNext(): Boolean = currentIndex < traversedList.size()
}

interface Collection<T> {
    fun newIterator(): Iterator<T>
}

class MyList<T> (private val elements: List<T>): Collection<T>
{
    fun size(): Int = elements.size
    fun elementAtIndex(index:Int) = elements[index]
    override fun newIterator() = ListIterator<T>(this)
}

fun main() {
    val l = MyList(listOf(1,2,3,4,5))
    val i = l.newIterator()
    while (i.hasNext())
        println(i.next())
}