package scratch

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
    fun doSomethingExtra() {
        println("doing something extra")
    }
}

class Decorator2(wrappedObject: SimpleClass): BaseDecorator(wrappedObject) {
    override fun doSomething() {
        wrappedObject.doSomething()
        doSomethingExtra()
    }
    fun doSomethingExtra() {
        println("doing something extra also")
    }
}

class Decorator3(wrappedObject: SimpleClass): BaseDecorator(wrappedObject) {
    override fun doSomething() {
        wrappedObject.doSomething()
        println("another extra behavior")
    }
}

fun main() {
    val instance = SimpleClass()
    val decoratedInstance = Decorator3(Decorator2(Decorator1(instance)))

    decoratedInstance.doSomething()
}