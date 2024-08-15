from abc import ABC,abstractmethod

class ProductA(ABC): #Plate
    @abstractmethod
    def someMethodA(self):
        pass

class ProductB(ABC): #Bowl
    @abstractmethod
    def someMethodB(self):
        pass

class ProductAVariant1(ProductA): #Ceramic Plate
    def someMethodA(self):
        print("im a ceramic")

class ProductBVariant1(ProductB): #Ceramic Bowl
    def someMethodB(self):
        print("im a product b variant 1")

class ProductAVariant2(ProductA): #Glass Plate
    def someMethodA(self):
        print("im a product a variant 2")

class ProductBVariant2(ProductB): #Glass Bowl
    def someMethodB(self):
        print("im a product b variant 2")

class AbstractFactory(ABC):
    @abstractmethod
    def newProductA(self) -> ProductA:
        pass

    @abstractmethod
    def newProductB(self) -> ProductB:
        pass

class FactoryVariant1(AbstractFactory): #Ceramic
    def newProductA(self) -> ProductA:
        return ProductAVariant1()
    def newProductB(self) -> ProductB:
        return ProductBVariant1()

class FactoryVariant2(AbstractFactory): #Glass
    def newProductA(self) -> ProductA:
        return ProductAVariant2()
    def newProductB(self) -> ProductB:
        return ProductBVariant2()

# def clientFunction(variant:AbstractFactory):
#     a:ProductA = variant.newProductA()
#     b:ProductB = variant.newProductB()
#     a.someMethodA()
#     b.someMethodB()

# v1 = FactoryVariant1()
# v2 = FactoryVariant2()
# clientFunction(v2)