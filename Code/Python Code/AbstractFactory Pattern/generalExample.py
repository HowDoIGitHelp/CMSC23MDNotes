from abc import ABC, abstractmethod
class ProductA(ABC):
    @abstractmethod
    def someMethodA(self) -> str:
        pass

class ProductB(ABC):
    @abstractmethod
    def someMethodB(self) -> str:
        pass

class AbstractFactory(ABC):
    @abstractmethod
    def newProductA(self) -> ProductA: #builds a product A
        pass
    @abstractmethod
    def newProductB(self) -> ProductB: #builds a product B
        pass

class ProductBVariant1(ProductB):
    def someMethodB(self) -> str:
        print("Im a product b of Variant1")

class ProductBVariant2(ProductB):
    def someMethodB(self) -> str:
        print("Im a product b of Variant2")

class ProductAVariant1(ProductA):
    def someMethodA(self) -> str:
        print("Im a product a of Variant1")

class ProductAVariant2(ProductA):
    def someMethodA(self) -> str:
        print("Im a product a of Variant2")

class FactoryVariant1(AbstractFactory):
    def newProductA(self) -> ProductA:
        return ProductAVariant1()

    def newProductB(self) -> ProductB:
        return ProductBVariant1()

class FactoryVariant2(AbstractFactory):
    def newProductA(self) -> ProductA:
        return ProductAVariant2()

    def newProductB(self) -> ProductB:
        return ProductBVariant2()

factory1 = FactoryVariant1()
factory2 = FactoryVariant2()

def clientFunction(factory:AbstractFactory):
    factory.newProductA().someMethodA()
    factory.newProductB().someMethodB()

clientFunction(factory1)
print()
clientFunction(factory2)
