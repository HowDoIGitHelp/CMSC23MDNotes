import math

def quotientUnsafe(a:float, b:float) -> float:
    return a/b


class DivisionByZeroError(Exception):
    def __init__(self,numerator:float, denominator:float):
        self.numerator = numerator
        self.denominator = denominator

def quotient(a:float, b:float) -> float: #maybe a float
    if b == 0:
        raise DivisionByZeroError(a,b)
    else:
        return a/b

def mixedFraction(a:float, b: float, c:float) -> float: #maybe a float
    return a + quotient(b,c)



def quotientString(a:float,b:float) -> str: #always a string
    try:
        wholePart = math.floor(quotient(a,b))
        decimalPart = quotient(a,b) - wholePart
        l = []
        print(l[0])
        return str(wholePart) + " and " + str(decimalPart)
    except Exception:
        return "undefined number"

def quotientList(l:list[float],m:list[float]) -> [float]: #always a list of float
    r = []
    for i in range(0,len(l)):
        try:
            r.append(quotient(l[i],m[i]))
        except DivisionByZeroError:
            r.append(math.inf)
        except IndexError:
            pass
    return r