class Matter:
    def __init__(self,name:str):
        self.__name = name
        self.__state = None #change this to the appropriate initial state (liquid)
    def changeState(self,newState):
        pass
    def compress(self):
        pass
    def release(self):
        pass
    def cool(self):
        pass
    def heat(self):
        pass
    def __str__(self):
        return "%s is currently a %s" % (self.__name,self.__state) #formatting strings just like you format strings in C
