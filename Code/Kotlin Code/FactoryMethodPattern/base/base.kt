package base

class DefaultProduct {
    fun use() {
        println("Using an instance of Default Product")
    }
}

class Factory {
    fun clientMethod() {
        val product = DefaultProduct()
        println("Using Product Dependency")
        product.use()
    }
}

fun main() {
    val f = Factory()
    f.clientMethod()
}