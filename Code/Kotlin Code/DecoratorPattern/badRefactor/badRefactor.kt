package badRefactor

open class SimpleClass(val extraBehavior1: Boolean = false, val extraBehavior2: Boolean = false) {
    open fun doSomething() {
        println("doing something")
        if (extraBehavior1) {
            println("doing something extra")
        }
        if (extraBehavior2) {
            println("doing something extra also")
        }
    }
}

fun main() {
    val instance1 = SimpleClass()
    instance1.doSomething()
    println()
    val instance2 = SimpleClass(extraBehavior1 = true)
    instance2.doSomething()
    println()
    val instance3 = SimpleClass(extraBehavior1 = true, extraBehavior2 = true)
    instance3.doSomething()
}
