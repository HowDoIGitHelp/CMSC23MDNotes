package role_interfaces

interface BreakableProp {
    fun destroy()
}

interface ClimbableProp {
    fun climb()
}

interface BurnableProp {
    fun burn()
}

interface InteractiveProp {
    fun interact()
}

class WoodenCrate : BreakableProp, ClimbableProp, BurnableProp {
    override fun destroy() {
        println("destroying crate")
    }

    override fun climb() {
        println("climbing crate")
    }

    override fun burn() {
        println("burning crate")
    }
}

class Shrine : ClimbableProp, InteractiveProp {
    override fun climb() {
        println("climbing shrine")
    }

    override fun interact() {
        println("entering shrine")
    }
}

class CampStove : BurnableProp, InteractiveProp {
    override fun burn() {
        println("lighting stove")
    }

    override fun interact() {
        println("opening cook menu")
    }

}

fun destroyProp(prop: BreakableProp) {
    prop.destroy()
}
