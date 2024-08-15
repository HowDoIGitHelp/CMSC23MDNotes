from noPattern import *

class ProductAVariant3(ProductA): #Clay Plate
    def someMethodA(self):
        print("im a product a variant 3")

class ProductBVariant3(ProductB): #Clay Bowl
    def someMethodB(self):
        print("im a product b variant 3")

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

v1 = FactoryVariant1()
v2 = FactoryVariant2()
v3 = FactoryVariant3()
clientFunction(v3)