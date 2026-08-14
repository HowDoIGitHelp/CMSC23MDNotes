from abc import ABC,abstractmethod

class Command(ABC):
    @abstractmethod
    def execute(self):
        pass
    @abstractmethod
    def __str__(self):
        pass
    @abstractmethod
    def undo(self):
        pass

class RealCommand1(Command):
    def __init__(self,param,receiver):
        self.__param = param
        self.__receiver = receiver
        self.__backup = receiver.copy()
    def execute(self):
        self.__receiver.performOperation1(self.__param)
    def undo(self):
        self.__receiver.restore(self.__backup)
    def __str__(self):
        return("Real Command 1 with params: %s received by %s" % (self.__param,self.__receiver))

class RealCommand2(Command):
    def __init__(self,param,otherParam,receiver):
        self.__param = param
        self.__otherParam = otherParam
        self.__receiver = receiver
        self.__backup = receiver.copy()
    def execute(self):
        self.__receiver.performOperation2(self.__param,self.__otherParam)
    def undo(self):
        self.__receiver.restore(self.__backup)
    def __str__(self):
        return("Real Command 2 with params: %s and %s received by %s" % (self.__param,self.__otherParam,self.__receiver))

class Receiver:
    def __init__(self,someAttribute:str):
        self.__someAttribute = someAttribute

    def performOperation1(self,param):
        self.__someAttribute += "attribute now changed, "
        print("operation1 executed with %s" % param)

    def performOperation2(self,param,otherParam):
        self.__someAttribute += "this attribute is new, "
        print("operation2 executed with %s and %s" % (param,otherParam))

    def someAttribute(self) -> str:
        return self.__someAttribute

    def restore(self,backup):
        self.__someAttribute = backup.someAttribute()

    def copy(self) -> 'Receiver':
        return Receiver(self.__someAttribute)

class Invoker:
    def __init__(self):
        self.__commandHistory = []

    def displayHistory(self):
        for command in self.__commandHistory:
            print(command)

    def invokeCommand1(self,param,receiver):
        command = RealCommand1(param,receiver)
        command.execute()
        self.__commandHistory.append(command)

    def invokeCommand2(self,param,otherParam,receiver):
        command = RealCommand2(param,otherParam,receiver)
        command.execute()
        self.__commandHistory.append(command)

    def undoPreviousCommand(self):
        undoneCommand = self.__commandHistory.pop()
        undoneCommand.undo()


r = Receiver("this")
i = Invoker()
i.invokeCommand1("foo",r)

print(r.someAttribute())

i.invokeCommand2("foo","bar",r)
print(r.someAttribute())

i.displayHistory()
i.undoPreviousCommand()
print(r.someAttribute())

i.undoPreviousCommand()
print(r.someAttribute())
