


class VoiceBox {
    fun speak() {
        println("Hi I'm a voicebox that speaks")
    }
}

open class Robot(n: String) {
    var name = n
    open fun talk() {
        println("Howdy it's me $name")
    }

    open fun communicate(partner: Robot) {
        println("Howdy ${partner.name} it's me $name")
    }
}

class SkyBot (name: String) : Robot(name) {
    fun fly(height: Int) {
        println("I'm flying ${height}m high in the air")
    }
}

open class ShadeBot(name: String, val visorOpacity: Float) : Robot(name) {
    override fun communicate(partner: Robot) {
        if (visorOpacity >= 1)
            println("Howdy, it's me $name. Sorry I cant see you my shades are too dark")
        else
            println("Howdy ${partner.name} it's me $name")
    }

}

class ShadeBotV2(n: String, v: Float) : ShadeBot(n, v) {
    fun method() {
        print("something")
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
    constructor (
        firstname: String,
        lastname: String
    ) : this("$firstname $lastname")

    constructor(
        firstname: String,
        lastname: String,
        middle_initial: Char,
    ) : this("$firstname $middle_initial. $lastname")
}

fun main() {
    val r = Robot("Chonk")
    val sr = SkyBot("Zonk")
    val sh = ShadeBot("Yonk", 2f)
    val sh2 = ShadeBotV2("Yonk2", 1f)

    println(sh2 is Robot)
    println(sh2 is ShadeBot)
    println(sh2 is ShadeBotV2)



    sh.communicate(sr)
}
