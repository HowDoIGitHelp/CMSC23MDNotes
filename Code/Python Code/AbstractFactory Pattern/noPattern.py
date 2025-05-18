from abc import ABC,abstractmethod


class ProductA(ABC):
    @abstractmethod
    def someMethodA(self):
        pass

class ProductB(ABC):
    @abstractmethod
    def someMethodB(self):
        pass

class AbstractFactory(ABC):
    @abstractmethod
    def newProductA(self) -> ProductA:
        pass
    @abstractmethod
    def newProductB(self) -> ProductB:
        pass

class FactoryVariant2(AbstractFactory): #Glass
    def newProductA(self) -> ProductA:
        return ProductAVariant2()
    def newProductB(self) -> ProductB:
        return ProductBVariant2()

class FactoryVariant1(AbstractFactory): #Ceramic
    def newProductA(self) -> ProductA:
        return ProductAVariant1()
    def newProductB(self) -> ProductB:
        return ProductBVariant1()

class ProductAVariant1: #Ceramic Plate
    def someMethodA(self):
        print('Ceramic Plate')


class ProductBVariant1: #Ceramic Bowl
    def someMethodB(self):
        print('Ceramic Bowl')

class ProductAVariant2: #Glass Plate
    def someMethodA(self):
        print('Glass Plate')

class ProductBVariant2: #Glass Bowl
    def someMethodB(self):
        print('Glass Bowl')



def clientFunction(variant:AbstractFactory):
    a = variant.newProductA()
    b = variant.newProductB()

    a.someMethodA()
    b.someMethodB()


f1 = FactoryVariant1()
f2 = FactoryVariant2()
#clientFunction(f2)