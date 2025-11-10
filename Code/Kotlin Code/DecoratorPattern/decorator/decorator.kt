package decorator

open class SimpleClass {
    open fun doSomething() {
        println("doing something")
    }
}

abstract class BaseDecorator(protected val wrappedObject: SimpleClass): SimpleClass() {
    abstract override fun doSomething()
}

class Decorator1(wrappedObject: SimpleClass): BaseDecorator(wrappedObject) {
    override fun doSomething() {
        wrappedObject.doSomething()
        doSomethingExtra()
    }

    private fun doSomethingExtra() {
        println("doing something extra")
    }

}

class Decorator2(wrappedObject: SimpleClass): BaseDecorator(wrappedObject) {
    override fun doSomething() {
        wrappedObject.doSomething()
        doSomethingExtra()
    }

    private fun doSomethingExtra() {
        println("doing something extra also")
    }

}

fun clientFunction(obj: SimpleClass) {
    obj.doSomething()
}

fun main() {
    val s = SimpleClass()
    val decoratedS = Decorator1(s)
    val decoratedS2 = Decorator2(s)

    clientFunction(s)
    println()

    clientFunction(decoratedS)
    println()

    clientFunction(Decorator1(Decorator2(s)))
    println()
}