def doubledInt(x:int) -> int:
    return x+x+1

def largest(x:float,y:float) -> float:
    return x if x > y else y
    
def isVertical(a:tuple[float,float], b:tuple[float,float]) -> bool:
    return a[0] == b[0] and a[1] != b[1]
    
def isPrime(n:int) -> bool:
    if n == 2:
        return True
    else:
        for i in range(2,(n//2)+2):
            if n % i == 0:
                return False
        return True


def primes(n:int) -> list[int]:
    i = 0
    primeList = []
    primeCandidate = 2
    while i < n:
        if isPrime(primeCandidate):
            i+=1
            primeList.append(primeCandidate)
        primeCandidate+=1
    return primeList
    
def fibonacciSequence(n:int) -> list[int]:
    fibList = [0,1]
    if n <= 2:
        return fibList[:n]
    else:
        i = 2
        while i < n:
            fibList.append(fibList[-1]+fibList[-2])
            i+=1
        return fibList

def combinedSortedPair(sortedl:list[int], sortedm:list[int]) -> list[int]:
    combinedList = []
    i = 0
    j = 0
    while i < len(sortedl) and j < len(sortedm):
        if sortedl[i] < sortedm[j]:
            combinedList.append(sortedl[i])
            i += 1
        else:
            combinedList.append(sortedm[j])
            j += 1
    
    if i < len(sortedl):
        combinedList = combinedList + sortedl[i:]
    if j < len(sortedm):
        combinedList = combinedList + sortedm[j:]
    return combinedList

def listHalves(l:list[int]) -> tuple[list[int],list[int]]:
    return (l[:(len(l)//2)],l[(len(l)//2):]) #tuple with left half on index zero and right half on index 1

def sortedIntegers(l:list[int]) -> list[int]:
    if len(l) < 2:
        return l
    else:
        left,right = listHalves(l)
    return combinedSortedPair(sortedIntegers(left),sortedIntegers(right))

def sublists(l:list[int]) -> list[list[int]]:
    lists = [[]]
    for i in range(len(l)):
        for j in range(i+1):
            lists.append(l[j:i+1])
    return lists
    
    