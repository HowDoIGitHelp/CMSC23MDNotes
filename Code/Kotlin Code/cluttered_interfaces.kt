class UnsuppportedMethod : Exception("unsupported method")

interface Prop {
    fun destroy()
    fun climb()
    fun burn()
    fun interact()
}

class WoodenCrate : Prop {
    override fun destroy() {
        println("destroying crate")
    }

    override fun climb() {
        println("climbing crate")
    }

    override fun burn() {
        println("burning crate")
    }

    override fun interact() {
        throw UnsupportedMethod()
    }
}

class Shrine : Prop {
    override fun destroy() {
        throw UnsupportedMethod()
    }

    override fun climb() {
        println("climbing shrine")
    }

    override fun burn() {
        throw UnsupportedMethod()
    }

    override fun interact() {
        println("entering shrine")
    }
}

class CampStove : Prop {
    override fun destroy() {
        throw UnsupportedMethod()
    }

    override fun climb() {
        throw UnsupportedMethod()
    }

    override fun burn() {
        println("lighting stove")
    }

    override fun interact() {
        println("opening cook menu")
    }

}

fun destroyProp(prop: Prop) {
    try {
        prop.destroy()
    } catch (ex: UnsupportedMethod) {
        println("prop is not destructible")
    }
}
