from abc import ABC,abstractmethod

class SimpleClass:
    def doSomething(self):
        print("This is a simple class")

class BaseDecorator(ABC,SimpleClass):
    def __init__(self, wrappedObject:SimpleClass):
        self._wrappedObject = wrappedObject

    @abstractmethod
    def doSomething(self):
        pass

class Decorator1(BaseDecorator):
    def doSomething(self):
        self._wrappedObject.doSomething()
        print("Decorated with decoration1")

class Decorator2(BaseDecorator):
    def doSomething(self):
        print("Decorated with decoration2")
        self._wrappedObject.doSomething()

"""
a = SimpleClass()
a.doSomething()

print()

b:SimpleClass = Decorator1(a)
b.doSomething()
print()
c = Decorator1(Decorator2(a))
c.doSomething()

print()
d = Decorator1(Decorator2(Decorator1(b)))
d.doSomething()

print(isinstance(d,SimpleClass))
"""