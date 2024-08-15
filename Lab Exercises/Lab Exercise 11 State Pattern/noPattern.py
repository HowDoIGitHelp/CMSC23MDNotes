from abc import ABC,abstractmethod

class State(ABC):
	@abstractmethod
	def contextRelatedMethod(self):
		pass
	@abstractmethod
	def anotherContextRelatedMethod(self):
		pass
	@abstractmethod
	def stateName(self) -> str:
		pass

class InitialState(State):
	def __init__(self, owner:'Context'):
		self.__owner = owner
	def contextRelatedMethod(self):
		print("manipulating the context on initial state\nchanging to another state") 
		self.__owner.state = AnotherState(self.__owner)
	def anotherContextRelatedMethod(self):
		print("manipulating the context on initial state\nno changes to state") 
	def stateName(self) -> str:
		return "Initial State"

class AnotherState(State):
	def __init__(self, owner:'Context'):
		self.__owner = owner
	def contextRelatedMethod(self):
		print('manipulating the context on another state\nno changes to state')
	def anotherContextRelatedMethod(self):
		print('manipulating the context on another state\nreturning to initial state')
		self.__owner.state = InitialState(self.__owner)
	def stateName(self) -> str:
		return "Another State"


class Context:
	def __init__(self):
		self.state = InitialState(self)
	def method(self):
		self.state.contextRelatedMethod()
	def anotherMethod(self):
		self.state.anotherContextRelatedMethod()


c = Context()
print(c.state.stateName())
print()
c.method()
print(c.state.stateName())
print()
c.method()
