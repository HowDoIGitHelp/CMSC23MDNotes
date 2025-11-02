# Lab Exercise 16 (Brute Force Search)

## Task

If you write brute force algorithms as search problems, they will have a common recipe. This is the reason why it is also called the exhaustive search algorithm. It will traverse the elements exhaustively in the search space, trying to check the validity of each element, until it completes the solution

**Equality Search**

Search for integers equal to the target

```kotlin
#searchSpace = mutableList(2,3,1,0,6,2,4)
#target = 2

var i = 0
val solutions: MutableList<Int> = mutableList()
val candidate = searchSpace[0]
while (i < searchSpace.size) {
    if (candidate == target)
        solutions.add(candidate)
    candidate = searchSpace[++i]
}
    
#solution = [2,2]
```

**Divisibility Search**

Search for integers divisible by the target

```kotlin
#searchSpace = [2,3,1,0,6,2,4]
#target = 2

var i = 0
val solutions: MutableList<Int> = mutableList()
val candidate = searchSpace[0]
while (i < searchSpace.size) {
    if (candidate % target == 0)
        solutions.add(candidate)
    candidate = searchSpace[++i]
}
    
#solution = [2,0,6,2,4]
```

**Minimum Search**

No target, searches for the smallest integer

```kotlin
#searchSpace = [2,3,1,0,6,2,4]
#target = None

var i = 1
val solutions: MutableList<Int> = mutableList(searchSpace[1])
val candidate = searchSpace[1]
while (i < searchSpace.size) {
    if (candidate <= target)
        solutions[0] = candidate
    candidate = searchSpace[++i]
}
    
#solution = [0]
```

**Common Recipe**

```kotlin
val solutions: MutableList<Int> = mutableList()
candidate = first()
while (isStillSearching()) {
    if (valid(candidate))
        updateSolution(candidate)
    candidate = next()
}
```

Because of this we can write a general brute force template method that would return the solution to brute force problems. To do this you create a superclass `SearchAlgorithm()` that contains the template method for brute force algorithms. If you want to customize this algorithm for special problems, all you have to do is to inherit from `SearchAlgorithm` and override only the necessary steps.

![brute force search](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/umlOutputs/BruteForceSearch.svg)

**Complete this system using the template pattern**

## Assessment Criteria

- Completeness of the pattern - 40
