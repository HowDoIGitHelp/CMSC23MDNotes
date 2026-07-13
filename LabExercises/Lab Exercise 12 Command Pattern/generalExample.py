from abc import ABC,abstractmethod

class Command(ABC):
    @abstractmethod
    def execute(self):
        pass
    @abstractmethod
    def __str__(self):
        pass

class RealCommand1(Command):
    def __init__(self,param,receiver):
        self.__param = param
        self.__receiver = receiver

    def execute(self):
        self.__receiver.performOperation1(self.__param)

    def __str__(self):
        return("Real Command 1 with params: %s received by %s" % (self.__param,self.__receiver))

class RealCommand2(Command):
    def __init__(self,param,otherParam,receiver):
        self.__param = param
        self.__otherParam = otherParam
        self.__receiver = receiver

    def execute(self):
        self.__receiver.performOperation2(self.__param,self.__otherParam)
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

class Invoker:

    def invokeCommand1(self,param,receiver):
        c = RealCommand1(param,receiver)
        command = c
        print(c)
        command.execute()

    def invokeCommand2(self,param,otherParam,receiver):
        command = RealCommand2(param,otherParam,receiver)
        command.execute()


r = Receiver("this ")
r2 = Receiver("that ")
i = Invoker()
i.invokeCommand1("foo",r)

print(r.someAttribute())

print()
i.invokeCommand2("foo","bar",r)
print(r.someAttribute())

print()
i.invokeCommand1("foo",r2)
print(r2.someAttribute())
print(r.someAttribute())
