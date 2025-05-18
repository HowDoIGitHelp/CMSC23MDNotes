from noPattern import *

class ProductAVariant3(ProductA): #Clay Plate
    def someMethodA(self):
        print("I'm a Clay Plate")

class ProductBVariant3(ProductB): #Clay Bowl
    def someMethodB(self):
        print("Clay Bowl")

class FactoryVariant3(AbstractFactory):#Clay
    def newProductA(self) -> ProductA:
        return ProductAVariant3()
    def newProductB(self) -> ProductB:
        return ProductBVariant3()

def clientFunction(variant:AbstractFactory):
    a:ProductA = variant.newProductA()
    b:ProductB = variant.newProductB()
    a.someMethodA()
    b.someMethodB()

 #clay variant
clientFunction(FactoryVariant2())