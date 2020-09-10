from abc import ABC,abstractmethod

class State(ABC):
    @abstractmethod
    def contextRelatedMethod(self):
        pass
    @abstractmethod
    def anotherContextRelatedMethod(self):
        pass
    @abstractmethod
    def stateName(self):
        pass

class InitialState(State):
    def __init__(self,context:'Context'):
        self.__context = context #this attribute is present only if you need a backreference to the context (if there are manipulations to context)
    def contextRelatedMethod(self):
        print("manipulating the context on initial state\nchanging to another state") #state methods may or may not change the state of the context this one does
        self.__context.changeState(AnotherState(self.__context))
    def anotherContextRelatedMethod(self):
        print("manipulating the context on initial state\nno changes in state")
    def stateName(self):
        return "Initial State"

class AnotherState(State):
    def __init__(self,context:'Context'):
        self.__context = context
    def contextRelatedMethod(self):
        print("manipulating the context on another state\nno changes in state")
    def anotherContextRelatedMethod(self):
        print("manipulating the context on another state\nchanging to initial state")
        self.__context.changeState(InitialState(self.__context))
    def stateName(self):
        return "Another State"

class Context:
    def __init__(self):
        self.__currentState = InitialState(self)
    def changeState(self,nextState:State):
        self.__currentState = nextState
    def method(self):
        self.__currentState.contextRelatedMethod() #delegation of behavior to attribute __currentState
    def anotherMethod(self):
        self.__currentState.anotherContextRelatedMethod() #delegation of behavior to attribute __currentState
    def display(self):
        print("context:")
        print("current state:" + self.__currentState.stateName())

c =  Context()
c.display()
print()
c.method()
print()
c.anotherMethod()
print()
c.method()
print()
c.method()
print()
c.display()
