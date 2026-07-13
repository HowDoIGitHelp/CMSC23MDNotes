# Abstract Factory Pattern General Example

### Problem

Your system consists of a family of related products. These products also have different variants.  You need a way to create these products so that the products match the the same variant. The exact variants of the family of  products are decided during runtime, somewhere else in the code (similar to product creation in a factory method) 

### Solution

You create different kinds of factories that realize under the same abstract factory. The exact type of factory will decide the variant of the family of products that are created. To do this you need to create different factory methods for each product. These factory methods must be abstract methods in the abstract factory so that every factory realization can create all members of the product family.

![abstract factory](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/abstractFactory.svg)

The family of products, are `ProductA` and `ProductB`, These products come in two variants, variant 1 and 2. `FactoryVariant1` is a realization of `Factory` which creates all of the product in variant 1 while `FactoryVariant2` creates all the products in variant 2.

> If it makes sense for the system,  you can make an abstract `Product` class for all the types of products.

When the client of an abstract factory produces its products, it doesn't need to know what kind of factory is producing the products. This means that the concrete type of a product (its variant) is not decided during compile time but instead it depends on the concrete type of the factory that is creating it.

```python
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
    a = factory.newProductA()
    b = factory.newProductB()
    
    a.someMethodA()
    b.someMethodB()

clientFunction(factory1)
print()
clientFunction(factory2)
```