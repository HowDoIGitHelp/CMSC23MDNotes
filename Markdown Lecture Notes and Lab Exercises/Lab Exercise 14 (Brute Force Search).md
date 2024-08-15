# Lab Exercise 14 (Brute Force Search)

## Task

If you write brute force algorithms as search problems, they will have a common recipe. This is the reason why it is called the exhaustive search algorithm. It will traverse all of the elements in the search space, trying to check the validity of each element, until it completes the solution

**Equality Search**

Search for integers equal to the target

```python
#searchSpace = [2,3,1,0,6,2,4]
#target = 2

i = 0
solutions = []
candidate = searchSpace[0]
while(i<len(searchSpace)):
    if candidate == target:
        solutions.append(candidate)
    candidate = searchSpace[++i]
    
#solution = [2,2]
```

**Divisibility Search**

Search for integers divisible by the target

```python
#searchSpace = [2,3,1,0,6,2,4]
#target = 2

i = 0
solutions = []
candidate = searchSpace[0]
while(i<len(searchSpace)):
    if candidate % target == 0:
        solution.append(candidate)
    candidate = searchSpace[++i]
    
#solution = [2,0,6,2,4]
```

**Minimum Search**

No target, searches for the smallest integer

```python
#searchSpace = [2,3,1,0,6,2,4]
#target = None

i = 1
solutions = [searchSpace[0]]
candidate = searchSpace[1]
while(i<len(searchSpace)):
    if candidate <= solutions[0]
        solutions[0] = candidate
    candidate = searchSpace[++i]
    
#solution = [0]
```

**Common Recipe**

```python
i = 0
solutions = []
candidate = first()
while(isStillSearching()):
    if valid(candidate):
        updateSolution(candidate)
    candidate = next()
```

Because of this we can write a general brute force template method that would return the solution to brute force problems. To do this you create a superclass `SearchAlgorithm()` that contains the template method for brute force algorithms. If you want to customize this algorithm for special problems, all you have to do is to inherit from `SearchAlgorithm` and override only the necessary steps.

![template example](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/templateexample.svg)

```python
class SearchAlgorithm(ABC):
    def __init__(self, target:int, searchSpace:[int]):
        self._searchSpace = searchSpace
        self._currentIndex = 0
        self._solutions = []
        self._target = target

    def bruteForceSolution(self):
        candidate = self.first()
        while(self.isSearching()):
            if self.isValid(candidate):
                self.updateSolution(candidate)
            candidate = self.next()
        return self._solutions

    def first(self) -> int:
        return self._searchSpace[0]

    def next(self) -> int:
        self._currentIndex += 1
        if self.isSearching():
            return self._searchSpace[self._currentIndex]

    def isSearching(self) -> bool:
        return self._currentIndex < len(self._searchSpace)

    @abstractmethod
    def isValid(self, candidate) -> bool:
        pass

    @abstractmethod
    def updateSolution(self, candidate):
        pass
```

> is `isValid()` and  `updateSolution(candidate)` is different for each algorithm so it doesn't have a default implementation. It would be best to make these steps abstract.

**Complete this system using the template pattern**

## Assessment Criteria

- Completeness of the pattern - 40
- Elegance of method and attribute naming - 10
