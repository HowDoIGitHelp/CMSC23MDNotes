package scratch

interface ProductA {
    fun show()
}

interface ProductB {
    fun use()
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

class FactoryVariant3: AbstractFactory {
    override fun newProductA(): ProductA = ProductAVariant3()

    override fun newProductB(): ProductB = ProductBVariant3()
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

class ProductAVariant3: ProductA {
    override fun show() {
        println("Using ProductAVariant3")
    }
}

class ProductBVariant3: ProductB {
    override fun use() {
        println("Using ProductBVariant3")
    }
}

class ProductAVariant4: ProductA {
    override fun show() {
        println("Using ProductAVariant4")
    }
}

class ProductBVariant4: ProductB {
    override fun use() {
        println("Using ProductBVariant4")
    }
}

class FactoryVariant4: AbstractFactory {
    override fun newProductA(): ProductA = ProductAVariant4()

    override fun newProductB(): ProductB = ProductBVariant4()
}

fun clientMethod(factory: AbstractFactory) {
    println("using products:")
    val productA = factory.newProductA()
    val productB = factory.newProductB()

    productA.show()
    productB.use()
}

fun main() {
    clientMethod(FactoryVariant3())
}