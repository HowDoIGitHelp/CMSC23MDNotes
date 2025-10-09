package badRefactor

interface Product {
    fun use()
}

class DefaultProduct: Product {
    override fun use() {
        println("Using an instance of Default Product")
    }
}

class SpecialProductA: Product {
    override fun use() {
        println("Using an instance of Special Product")
    }
}

class Factory {
    fun clientMethod(productType: Int) {
        val product = when (productType) {
            1 -> DefaultProduct()
            2 -> SpecialProductA()
            else -> DefaultProduct()
        }
        println("Using Product Dependency")
        product.use()
    }
}

fun main() {
    val f = Factory()
    f.clientMethod(1)
    f.clientMethod(2)
}
