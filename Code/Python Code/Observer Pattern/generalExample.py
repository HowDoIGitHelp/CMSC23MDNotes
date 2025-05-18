from abc import ABC, abstractmethod

class Observer(ABC):
    @abstractmethod
    def update(self,updatedSubject:str):
        pass

class Publisher:
    def __init__(self,subject:str):
        self.__subject = subject
        self.__subscribers:List[Observer] = []

    def manipulateSubject(self,updatedSubject:str):
        self.__subject = updatedSubject
        self.notifyObservers()

    def subscribe(self,newSubscriber:Observer):
        self.__subscribers.append(newSubscriber)
        newSubscriber.update(self.__subject)

    def unsubscribe(self,exSubscriber:Observer):
        self.__subscribers.remove(exSubscriber)

    def notifyObservers(self):
        for sub in self.__subscribers:
            sub.update(self.__subject)

class RealObserver1(Observer):
    def update(self,updatedSubject:str):
        print('subject has changed to '+ updatedSubject)

class RealObserver2(Observer):
    def __init__(self):
        self.subjectCopy = ''

    def update(self,updatedSubject:str):
        self.subjectCopy = updatedSubject