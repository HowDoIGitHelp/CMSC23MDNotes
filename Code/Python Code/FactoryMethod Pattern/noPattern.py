from abc import ABC, abstractmethod

class Product(ABC):
    @abstractmethod
    def show(self):
        pass

class DefaultProduct(Product):
    def show(self):
        print("I am an instance of product")

class SpecialProductA(Product):
    def show(self):
        print("I am an instance of special product")

class SpecialProductB(Product):
    def show(self):
        print("I am an instance of special product b")

class Factory:
    def printANewProduct(self):
        p = self.newProduct()
        p.show()

    def newProduct(self):
        return DefaultProduct()

class SpecialFactoryA(Factory):
    def newProduct(self):
        return SpecialProductA()

class SpecialFactoryB(Factory):
    def newProduct(self):
        return SpecialProductB()

f:Factory = Factory()
f.printANewProduct()