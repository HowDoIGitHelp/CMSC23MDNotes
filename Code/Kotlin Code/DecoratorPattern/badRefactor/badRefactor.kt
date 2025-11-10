package badRefactor

open class SimpleClass(val enabledBehavior1: Boolean = false, val enabledBehavior2: Boolean = false, val enabledBehavior3: Boolean = false) {
    open fun doSomething() {
        println("doing something")
        if (enabledBehavior1) {
            println("doing something extra")
        }
        if (enabledBehavior2) {
            println("doing something extra also")
        }
        if (enabledBehavior3) {
            println("doing something extra again")
        }
    }
}

fun main() {
    val instance1 = SimpleClass()
    instance1.doSomething()
    println()
    val instance2 = SimpleClass(enabledBehavior1 = true)
    instance2.doSomething()
    println()
    val instance3 = SimpleClass(enabledBehavior1 = true, enabledBehavior2 = true)
    instance3.doSomething()
}

