package abstractFactory

interface ProductA {
    fun show()
}

interface ProductB {
    fun use()
}

class ProductAVariant1: ProductA {
    override fun show() {
        println("Using ProductAVariant1")
    }
}

class ProductBVariant1: ProductB {
    override fun use() {
        println("Using ProductBVariant1")
    }
}

class ProductAVariant2: ProductA {
    override fun show() {
        println("Using ProductAVariant2")
    }
}

class ProductBVariant2: ProductB {
    override fun use() {
        println("Using ProductBVariant2")
    }
}

interface AbstractFactory {
    fun newProductA(): ProductA
    fun newProductB(): ProductB
}

class FactoryVariant1: AbstractFactory {
    override fun newProductA(): ProductA = ProductAVariant1()
    override fun newProductB(): ProductB = ProductBVariant1()
}

class FactoryVariant2: AbstractFactory {
    override fun newProductA(): ProductA = ProductAVariant2()
    override fun newProductB(): ProductB = ProductBVariant2()
}

fun clientMethod(factory: AbstractFactory) {
    println("using products:")
    val productA = factory.newProductA()
    val productB = factory.newProductB()
    productA.show()
    productB.use()
}

fun main(args: Array<String>) {
    clientMethod(FactoryVariant2())
}
