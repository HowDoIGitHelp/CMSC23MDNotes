package scratch

interface Product {
    fun use()
}

class DefaultProduct : Product {
    override fun use() {
        println("Using an instance of Default Product")
    }
}

class SpecialProduct : Product{
    override fun use() {
        println("Using an instance of Special Product")
    }
}

class AnotherSpecialProduct : Product {
    override fun use() {
        println("Using an instance of Another Special Product")
    }
}

class AnotherSpecialFactory : Factory() {
    override fun newProduct(): Product {
        return AnotherSpecialProduct()
    }
}

class SpecialFactory : Factory() {
    override fun newProduct(): Product {
        return SpecialProduct()
    }
}

open class Factory {
    fun clientMethod() {
        val product = newProduct()
        println("Using Product Dependency")
        product.use()
    }

    open fun newProduct(): Product {
        return DefaultProduct()
    }
}

fun main() {
    val f = AnotherSpecialFactory()
    f.clientMethod()
}
