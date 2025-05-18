from abc import ABC,abstractmethod

class SimpleClass:
    def doSomething(self):
        print("This is a simple class")

class BaseDecorator(ABC,SimpleClass):
    def __init__(self, wrappedObject):
        self.wrappedObject = wrappedObject

    @abstractmethod
    def doSomething(self):
        pass

class Decorator1(BaseDecorator):
    def doSomething(self):
        self.wrappedObject.doSomething()
        print("Decorated with decoration1")

class Decorator2(BaseDecorator):
    def doSomething(self):
        print("Decorated with decoration2")
        self.wrappedObject.doSomething()


