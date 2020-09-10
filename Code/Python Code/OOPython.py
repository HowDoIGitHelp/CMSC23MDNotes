from abc import ABC, abstractmethod

class EmptyClass:
    pass

class VoiceBox:
    name : str = "Vincent"
    def speak():
        print("Hi, I'm " + VoiceBox.name + " the VoiceBox")

#VoiceBox.speak()
#VoiceBox.name = "Vito"
#VoiceBox.speak()
#v : VoiceBox = VoiceBox()

class Robot:
    def __init__(self, n:str):
        self.name = n

    def talk(self):
        print("Howdy, it's me, "+ self.name)

    def communicate(self, partner : 'Robot'):
        print("Howdy, "+ partner.name + " it's me, "+ self.name)

r1 : Robot = Robot("Bonk")
r2 : Robot = Robot("Chonk")

#r1.talk()
#r2.talk()

r1.name = "Donk"

#r2.communicate(r1)

class SkyBot(Robot):
    def fly(self, height:int):
        print("I'm "+ str(height) +"m high in the air. Skybot go zoom. ")

r3:SkyBot = SkyBot("Zonk")
#r3.talk()
#r3.communicate(r3)
#r3.fly(3)

class ShadeBot(Robot):
    def __init__(self, n:str, o:float):
        self.name = "Mr. " + n
        self.visorOpacity = o

    def communicate(self,partner:Robot):
        if self.visorOpacity >= 1:
            print("Howdy, it's me, "+ self.name + ". Sorry I cant see you my shades are too dark")
        else:
            print("Howdy, "+ partner.name + " it's me, "+ self.name)

r4:ShadeBot = ShadeBot("Tonk", 1)
#4.talk()
#r4.communicate(r3)



class BorrowableItem(ABC):
    @abstractmethod
    def borrow(self):
        pass

    @abstractmethod
    def name(self) -> str:
        pass


    #def implementationRequirement(self):
        #pass

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

#b.borrow()
#i.borrow()


class ClandestineClass:
    def __init__(self, publicValue:int, protectedValue:int, privateValue:int):
        self.publicValue = publicValue
        self._protectedValue = protectedValue
        self.__privateValue = privateValue


    def doPublicly(self):
        print("Hey!, these are my values")
        print(self.publicValue)
        print(self._protectedValue)
        print(self.__privateValue)

    def _doProtectedly(self):
        print("hey")

    def __doPrivately(self):
        print("...")

class SpecialClandestineClass(ClandestineClass):
    def doSomethingSpecial(self):
        print("Do somethin inside special clandestine class")
        print(self.publicValue)
        print(self._protectedValue)
        print(self.__privateValue)

c:ClandestineClass = ClandestineClass(1,2,3)
#print(c.publicValue)
#print(c._protectedValue)
#print(c.__privateValue)
#c.doPublicly()


class AbstractClass(ABC):
    @abstractmethod
    def printSomethingA(self):
        pass

    def printSomethingB(self):
        print("I'm inherited. You can also override me if you want")

class ConcreteClass1(AbstractClass):
    def printSomethingA(self):
        print("I'm implemented by Concrete Class 1")

class ConcreteClass2(AbstractClass):
    def printSomethingA(self):
        print("I'm implemented by Concrete Class 2")

    def printSomethingB(self):
        print("I'm overriden by Concrete Class 2")

c1:AbstractClass = ConcreteClass1()
c2:AbstractClass = ConcreteClass2()

c1.printSomethingA()
c1.printSomethingB()
print()
c2.printSomethingA()
c2.printSomethingB()



s:SpecialClandestineClass = SpecialClandestineClass(1,2,3)
s.doPublicly()
print() #prints a new line for formatting
s.doSomethingSpecial()
