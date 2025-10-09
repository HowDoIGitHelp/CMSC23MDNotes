package factoryMethod

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
        println("Using an instance of Special Product A")
    }
}

class SpecialProductB: Product {
    override fun use() {
        println("Using an instance of Special Product B")
    }
}

open class Factory {

    open fun clientMethod() {
        val product = newProduct()
        println("Using Product Dependency")
        product.use()
    }

    open fun newProduct(): Product = DefaultProduct()
}

class SpecialFactoryA: Factory() {
    override fun newProduct(): Product = SpecialProductA()
}

class SpecialFactoryB: Factory() {
    override fun newProduct(): Product = SpecialProductB()
}

fun main() {
    val f = SpecialFactoryA()
    f.clientMethod()
}
