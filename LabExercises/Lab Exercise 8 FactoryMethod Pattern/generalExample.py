from abc import ABC, abstractmethod

class Product(ABC):
    @abstractmethod
    def show(self) -> str:
        pass

class DefaultProduct(Product):
    def show(self) -> str:
        print("I'm an instance of default product")

class SpecialProductA(Product):
    def show(self) -> str:
        print("I'm an instance of special product a")

class SpecialProductB(Product):
    def show(self) -> str:
        print("I'm an instance of special product b")

class Factory:
    def printANewProduct(self):
        p = self.newProduct()
        p.show()

    def newProduct(self) -> Product:
        return DefaultProduct()

class SpecialFactoryA(Factory):
    def newProduct(self) -> Product:
        return SpecialProductA()

class SpecialFactoryB(Factory):
    def newProduct(self) -> Product:
        return SpecialProductB()

f:Factory = Factory()
f.printANewProduct()

g:Factory = SpecialFactoryA()
g.printANewProduct()

h:Factory = SpecialFactoryB()
h.printANewProduct()
