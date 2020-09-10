from abc import ABC, abstractmethod

class AbstractService(ABC):
    @abstractmethod
    def serviceMethod1(self):
        pass
    @abstractmethod
    def serviceMethod2(self,data:str):
        pass

class RealService(AbstractService):
    def serviceMethod1(self):
        print("method1 of RealService")

    def serviceMethod2(self,data:str):
        print("method2 of RealService with "+ data)

class RequiredInterface(ABC):
    @abstractmethod
    def method1(self):
        pass

    @abstractmethod
    def method2(self,data:str):
        pass

class ServiceAdapter(RequiredInterface):
    def __init__(self, service:AbstractService):
        self.__service = service

    def method1(self):
        self.__service.serviceMethod1()

    def method2(self,data:str):
        self.__service.serviceMethod2(data)


s:AbstractService = RealService()
a:RequiredInterface = ServiceAdapter(s)

a.method1()
a.method2("foo")
