# Factory Method Pattern General Example

### Problem

The exact type of the dependency (a product) created and used by some client (a factory) is decided by a client of that factory. Somewhere, inside this factory class, a specific product is being instantiated and maybe used (this instantiation happens maybe more than once). But, as it turns out, there are different types of products, (there's also the possibility of more product types in the future). You can change the code of the factory class to accommodate multiple product types. For every product, you modify the factory and add some if-else clause to produce the correct product type.

As you see this process is quite tedious. For every new product type that is added to your system, you perform surgery to the factory class. This process will end up forcing you to create smelly if-else checks to switch to the correct product type.

### Solution

You encapsulate the creation of a class inside a **factory method** that is specified to return an abstraction of the product. If there are other real product types that have to be produced, you create a specialized factory which overrides the factory method.

![factory method class diagram](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/factorymethod.svg)

> Somewhere inside factory you have one or more instances of creating or using the product.

If you choose to build `Factory` as a concrete superclass,  the factory method inside the `Factory` should return some realization of `Product`. This is the default product returned by any `Factory`. If you need to return a different `Product` realization, you override the factory method to return that particular `Product` realization.

### Sample Code

```python
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

f:Factory = Factory()
f.printANewProduct()

g:Factory = SpecialFactoryA()
g.printANewProduct()

h:Factory = SpecialFactoryB()
h.printANewProduct()
```

