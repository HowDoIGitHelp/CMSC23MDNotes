package badRefactor2

open class SimpleClass {
    open fun doSomething() {
        println("doing something")
    }
}

open class ExtraClass1 : SimpleClass() {
    override fun doSomething() {
        println("doing something")
        println("doing something extra")
    }
}

open class ExtraClass2 : SimpleClass() {
    override fun doSomething() {
        println("doing something")
        println("doing something extra also")
    }
}

open class ExtraClass3 : SimpleClass() {
    override fun doSomething() {
        println("doing something")
        println("doing something extra")
        println("doing something extra also")
    }
}

fun main() {
    val instance1 = SimpleClass()
    instance1.doSomething()
    println()

    val instance2 = ExtraClass1()
    instance2.doSomething()
    println()
}
