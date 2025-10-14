class EmptyClass

class VoiceBox {
    fun speak() {
        println("Hi I'm a voicebox that speaks")
    }
}

open class Robot constructor (var name: String) {
    open fun talk() {
        println("Howdy it's me $name")
    }

    open fun communicate(partner: Robot) {
        println("Howdy ${partner.name} it's me $name")
    }

}

class RoboticArm (val type: Char) { //if there are no visibility modifiers, the `constructor` keyword can be ommitted

    var position = 0.0f

    fun move(amount: Float) {
        position = position + amount
    }

    fun reset() {
        position = 0.0f
    }

}

class Camera {

    init {
        println("startup.")
        cleanSensor()
    }

    fun cleanSensor() {
        println("cleaning sensor...")
        println("cleaning complete")
    }

}

class Person (val name: String) {
    constructor (firstname: String, lastname: String): this("$firstname $lastname")
    constructor (firstname: String, mi: Char, lastname: String): this("$firstname $mi. $lastname")
}

class SkyBot (name: String) : Robot(name) {
    fun fly(height: Int) {
        println("I'm flying ${height}m high in the air")
    }
}

class ShadeBot(name: String, val visorOpacity: Float) : Robot(name) {

    override fun communicate(partner: Robot) {
        if (visorOpacity >= 1)
            println("Howdy, it's me $name. Sorry I cant see you my shades are too dark")
        else
            println("Howdy ${partner.name} it's me $name")
    }

}

interface BorrowableItem {
    fun borrow()
    fun name(): String
}

class Book (val title: String): BorrowableItem {
    override fun borrow() {
        println("I am a book called ${name()} and I'm being borrowed")
    }

    override fun name(): String{
        return title
    }
}

class IMacUnit(val id: Int): BorrowableItem {

    override fun borrow() {
        println("I am an imac called ${name()} and I'm being borrowed")
    }

    override fun name(): String {
        return "iMac$id"
    }
}

open class ClandestineClass (
    public var v1: Int,
    protected var v2: Int,
    private var v3: Int
) { //the syntax above is just a way to make the formatted constructor look neater
    public open fun method1() {
        println("Hey, these are my values")
        println("$v1, $v2, $v3")
    }

    protected open fun method2() {
        println("Hey!")
    }

    private fun method3() {
        println("...")
    }

}

open class SpecialClandestineClass1(
    v1: Int,
    v2: Int,
    v3: Int
) : ClandestineClass(v1,v2,v3) {

    public override fun method2() {
        println("Hey! Attempting to display values")
        println("$v1, $v2")
    }

}

abstract class AbsClass(val state: String) {

    abstract fun printSomethingA()

    open fun printSomethingB() {
        println("I'm inherited, you can also override me if you want")
    }

}

class ConcreteClass1(state: String) : AbsClass(state) {

    override fun printSomethingA() {
        println("I'm implemented by ConcreteClass1")
    }

}

class ConcreteClass2(state: String) : AbsClass(state) {

    override fun printSomethingA() {
        println("I'm implemented by ConcreteClass2")
    }

    override fun printSomethingB() {
        println("I'm overriden by ConcreteClass2")
    }

}

@Suppress("USELESS_IS_CHECK")
fun main() {
    val secretiveObject = ClandestineClass(1,2,3)
    val secretiveObject2 = SpecialClandestineClass1(1,2,3)
    secretiveObject2.method2()
}