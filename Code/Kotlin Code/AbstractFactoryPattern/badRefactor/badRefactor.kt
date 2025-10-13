package badRefactor

class ProductAVariant1 {
    fun show() {
        println("Using ProductAVariant1")
    }
}

class ProductBVariant1 {
    fun use() {
        println("Using ProductBVariant1")
    }
}

class ProductAVariant2 {
    fun show() {
        println("Using ProductAVariant2")
    }
}

class ProductBVariant2 {
    fun use() {
        println("Using ProductBVariant2")
    }
}

fun clientMethod(chosenVariant: Int) {
    println("using products:")
    when(chosenVariant) {
        1 -> {
            val productA = ProductAVariant1()
            val productB = ProductBVariant1()
            productA.show()
            productB.use()
        }
        2 -> {
            val productA = ProductAVariant2()
            val productB = ProductBVariant2()
            productA.show()
            productB.use()
        }
    }
}

fun main(args: Array<String>) {
    clientMethod(args[0].toInt())
}