from abc import ABC,abstractmethod

class Strategy(ABC):
	@abstractmethod
	def execute(self,params:str):
		pass

class RealStrategy1(Strategy):
	def execute(self, params:str):
		print('I am some behavior with parameters: ' + params )

class RealStrategy2(Strategy):
	def execute(self, params:str):
		print('I behavior2 with parameters: ' + params )

class RealStrategy3(Strategy):
	def execute(self, params:str):
		print('Im a new strategy')

def behavior(params:str):
	print('I am some behavior with parameters: ' + params )

def behavior2(params:str):
	print('I behavior2 with parameters: ' + params )


def clientMethod(behavior:Strategy):
	behavior.execute('this')


