from abc import ABC,abstractmethod

class Strategy(ABC):
    @abstractmethod
    def execute(self,params):
        pass

class RealStrategy1(Strategy):
    def execute(self,params):
        print("real strategy 1 %s" % params)

class RealStrategy2(Strategy):
    def execute(self,params):
        print("real strategy 2 %s" % params)

class Client:
    def higherOrderMethod(self, s:Strategy):
        data = "literal String"
        print("parametrized behavior")
        s.execute(data)


s = Client()
strat1:Strategy = RealStrategy1()
s.higherOrderMethod(strat1)
print()
s.higherOrderMethod(RealStrategy2())
