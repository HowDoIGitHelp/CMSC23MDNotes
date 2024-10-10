class EmptyClass:
    pass


class VoiceBox:
    name = "Vincent"
    def speak():
        print("Hi, I'm " + VoiceBox.name + " the VoiceBox")

#VoiceBox.speak()
#VoiceBox.name = "Vito"
#VoiceBox.speak()



class Robot:
    #dunder function
    def __init__(self, n:str):
        self.name = n
        self.attr2 = "val1"
        self.attr3 = 0
        #r1.name = "Bonk"

    def talk(self):
        #r1.name
        self.attr4 = 0
        print("Howdy, it's me, "+ self.name)

    def communicate(self, partner : 'Robot'):
        print("Howdy, "+ partner.name + " it's me, "+ self.name)


r1 = Robot("Bonk")
r2 = Robot("Chonk")

#print(r1.name)
#print(r2.name)

#r1.talk()
#r2.talk()

#r1.name = "Donk"

#r1.talk()
#r2.communicate(r1)

class SkyBot(Robot):
    def fly(self, height:int):
        print("I'm "+ str(height) +"m high in the air. Skybot go zoom. ")

r3:SkyBot = SkyBot("Zonk")
r3.talk()
r3.communicate(r3)
r3.fly(3)

class ShadeBot(Robot):
    #override
    def __init__(self, n:str, o:float):
        self.name = "Mr. " + n
        self.visorOpacity = o

    #override
    def communicate(self,partner:Robot):
        if self.visorOpacity >= 1:
            print("Howdy, it's me, "+ self.name + ". Sorry I cant see you my shades are too dark")
        else:
            print("Howdy, "+ partner.name + " it's me, "+ self.name)

r4:ShadeBot = ShadeBot("Tonk", 1)
r4.talk()
r4.communicate(r4)
print(isinstance(r4,Robot)) #True
print(isinstance(r4,ShadeBot)) #True
print(isinstance(r4,SkyBot)) #False

r1:Robot = Robot("Onk")
print(isinstance(r1,Robot)) #True
print(isinstance(r1,SkyBot)) #False


from abc import ABC, abstractmethod
#Abstract Base Class
class BorrowableItem(ABC):
    @abstractmethod        
    def borrow(self):
        pass

    @abstractmethod
    def name(self) -> str:
        pass

class Book(BorrowableItem):
    def __init__(self, title:str):
        self.title = title

    def borrow(self):
        print("I'm a book called "+ self.name() +" and I'm being borrowed")

    def name(self) -> str:
        return self.title

class IMacUnit(BorrowableItem):
    def __init__(self, id:int):
        self.id = id

    def borrow(self):
        print("I'm an iMac called "+ self.name() +" and I'm being borrowed")

    def name(self) -> str:
        return "iMac" + str(self.id)

b : BorrowableItem = Book("Necronomicon")
i : BorrowableItem = IMacUnit(5)

items = [b,i]

for item in items:
    item.borrow()

print(isinstance(b,BorrowableItem)) #True
print(isinstance(b,Book)) #True

class ClandestineClass:
    def __init__(self, publicValue:int, protectedValue:int, privateValue:int):
        self.publicValue = publicValue#1
        self._protectedValue = protectedValue
        self.__privateValue = privateValue

    def doPublicly(self):
        print("Hey!, these are my values")
        print(self.publicValue)
        print(self._protectedValue)
        print(self.__privateValue)

    def _doProtectedly(self):
        self.__doPrivately()
        print("hey")

    def __doPrivately(self):
        print("...")

class SpecialClandestineClass(ClandestineClass):
    def doSomethingSpecial(self):
        print("Do somethin inside special clandestine class")
        print(self.publicValue)
        print(self._protectedValue)

#c:ClandestineClass = ClandestineClass(1,2,3)
#print(c.publicValue)
#print(c._protectedValue)
#print(c.__privateValue)
#c.doPublicly()
#c._doProtectedly()
#c.__doPrivately()

s : SpecialClandestineClass = SpecialClandestineClass(1,2,3)
s.doSomethingSpecial()

class AbstractClass(ABC):
    @abstractmethod
    def printSomethingA(self):
        pass

    def printSomethingB(self):
        print("I'm a default method from Abstract Class")


class ConcreteClass1(AbstractClass):
    def printSomethingA(self):
        print("I'm implemented by Concrete Class 1")

class ConcreteClass2(AbstractClass):
    def printSomethingA(self):
        print("I'm implemented by Concrete Class 2")
    #override
    def printSomethingB(self):
        print("I'm overriden by Concrete Class 2")

c1:AbstractClass = ConcreteClass1()
c2:AbstractClass = ConcreteClass2()

#c1.printSomethingA()
#c1.printSomethingB()
print()
c2.printSomethingA()
c2.printSomethingB()



#s:SpecialClandestineClass = SpecialClandestineClass(1,2,3)
#s.doPublicly()
#print() #prints a new line for formatting
#s.doSomethingSpecial()
