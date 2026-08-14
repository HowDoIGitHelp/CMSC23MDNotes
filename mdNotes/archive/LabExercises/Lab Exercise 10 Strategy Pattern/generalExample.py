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
    def higherOrderMethod(self, s:Strategy, params:str):
        print("parametrized behavior:")
        s.execute(params)


s = Client()
strat1:Strategy = RealStrategy1()
s.higherOrderMethod(strat1,"data")
print()
s.higherOrderMethod(RealStrategy2(),"data")
