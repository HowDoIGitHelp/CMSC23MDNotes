from abc import ABC, abstractmethod
class Observer(ABC):
    @abstractmethod
    def update(self,subject:str):
        pass

class Publisher:
    def __init__(self,subject:str):
        self.__subject = subject # this could be any object that is interesting to observers
        self.__subscribers = []

    def subject(self) -> str:
        return self.__subject

    def manipulateSubject(self, someString:str):
        self.__subject = someString
        self.notifyObservers()

    def subscribe(self, observer:Observer):
        self.__subscribers.append(observer)
        observer.update(self.__subject)

    def unsubscribe(self, observer:Observer):
        if observer in self.__subscribers:
            self.__subscribers.remove(observer)

    def notifyObservers(self):
        for observer in self.__subscribers:
            observer.update(self.__subject)

class RealObserver1(Observer):
    def __init__(self,name:str):
        self.__name = name
        self.__subject = None

    def update(self,subject:str): # everything that the observers should be notified of should be passed here
        self.__subject = subject
        print("Subject has been updated to " + self.__subject)
        print("Performing observer1 behavior in response to change")

class RealObserver2(Observer):
    def __init__(self,name:str):
        self.__name = name
        self.__subject = None

    def update(self,subject:str):
        self.__subject = subject
        print("Subject has been updated to " + self.__subject)
        print("Performing observer2 behavior in response to change")

p = Publisher("initial value")
o1 = RealObserver1("o1")
o2 = RealObserver2("o2")

p.subscribe(o1)
p.subscribe(o2)
print()

p.manipulateSubject("new value")
print()

p.unsubscribe(o1)

p.manipulateSubject("newer value")

p.unsubscribe(o2)

print()
p.manipulateSubject("newest value")
