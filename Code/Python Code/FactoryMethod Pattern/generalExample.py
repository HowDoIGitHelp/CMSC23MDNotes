from abc import ABC, abstractmethod

class Product(ABC):
    @abstractmethod
    def show(self):
        pass

class DefaultProduct(Product):
    def show(self):
        print("I'm an instance of default product")

class SpecialProductA(Product):
    def show(self):
        print("I'm an instance of special product a")

class SpecialProductB(Product):
    def show(self):
        print("I'm an instance of special product b")

class SpecialProductC(Product):
    def show(self):
        print("I'm an instance of the newest product type")

class Factory:
    def printANewProduct(self):
        p = self.newProduct()
        p.show()

    def newProduct(self) -> Product: #factory method
        return DefaultProduct()

class SpecialFactoryA(Factory):
    def newProduct(self) -> Product:
        return SpecialProductA()

class SpecialFactoryB(Factory):
    def newProduct(self) -> Product:
        return SpecialProductB()

class SpecialFactoryC(Factory):
    def newProduct(self) -> Product:
        return SpecialProductC()

f:Factory = Factory()
f.printANewProduct()

g:Factory = SpecialFactoryA()
g.printANewProduct()

h:Factory = SpecialFactoryB()
h.printANewProduct()

i:Factory = SpecialFactoryC()
i.printANewProduct()