class Fraction:
    def __init__(self,num:int,denom:int):
        self.__num = num
        self.__denom = denom
    def num(self):
        return self.__num
    def denom(self):
        return self.__denom
    def __str__(self) -> str:
        return str(self.__num) + "/" + str(self.__denom)

class Calculation:
    def __init__(self,left:Fraction,right:Fraction,operation:Operation): #will cause an error when ran since Operation does not exist yet
        self.__left = left
        self.__right = right
        self.__operation = operation #the parameter that represents the operation
        self.__answer = None #the answer should be calculated here

    def __str__(self):
        return str(self.__left) + " " + str(self.__operation) + " " + str(self.__right) + " = " + str(self.__answer)


f:Fraction = Fraction(1,4)
print(f)
