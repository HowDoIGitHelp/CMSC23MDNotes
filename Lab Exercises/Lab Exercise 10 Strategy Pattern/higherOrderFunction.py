from abc import ABC, abstractmethod

class Strategy(ABC):
    @abstractmethod
    def execute(self,param:str):
        pass

class RealStrategy1(Strategy):
    def execute(self, param:str):
        print(f"Strategy 1 {param}")

class RealStrategy2(Strategy):
    def execute(self, param:str):
        print(f"Strategy 2 {param}")

class Client:
    def higherOrderMethod(self, strat:Strategy, param):
        print("higher order method:")
        strat.execute(param)

c = Client()
c.higherOrderMethod(RealStrategy1(),"foo")
