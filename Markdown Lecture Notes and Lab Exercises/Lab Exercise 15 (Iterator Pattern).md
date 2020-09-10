# Lab Exercise 15 (Iterator Pattern)

## Task

For non built-in collections, you can create an iterator that does the traversal for you. On the bare minimum these iterators will realize some `Iterator` abstraction that contains the methods, `next()`, and `hasNext()`. From these methods alone you can easily perform complete traversals without knowing the exact type of the collection:

```python
i = collection.newIterator()
while i.hasNext():
	print(i.next())
```

![iterator](C:/Users/rrabe/Google Drive/Lecture-Notes-And-Resources/CMSC 23/uml/iterator.svg)

```python
from abc import ABC,abstractmethod

class Iterator(ABC):
    @abstractmethod
    def next(self):
        pass

    @abstractmethod
    def hasNext(self):
        pass

class Collection(ABC):
    @abstractmethod
    def newIterator(self):
        pass

class MyList(Collection):
    def __init__(self, elements):
        self.__elements = elements

    def size(self):
        return len(self.__elements)

    def elementAtIndex(self,index):
        return self.__elements[index]

    def newIterator(self):
        return ListIterator(self)

class ListIterator(Iterator):
    def __init__(self, list):
        self.__traversedList = list
        self.__currentIndex = 0

    def next(self):
        self.__currentIndex += 1
        return self.__traversedList.elementAtIndex(self.__currentIndex-1)

    def hasNext(self):
        return self.__currentIndex < self.__traversedList.size()

#OPTIONAL
"""
class MyBTree(Collection):
    def __init__(self,value:int,left:'MyBTree' = None,right:'MyBTree' = None):
        self.__value = value
        self.__left = left
        self.__right = right

    def left(self):
        return self.__left

    def right(self):
        return self.__right

    def value(self):
        return self.__value

    def newIterator(self):
        return InOrderIterator(self)
"""


c:Collection = MyList([1,2,3,4])
iter:Iterator = c.newIterator()

while(iter.hasNext()):
    print(iter.next())
```

The `hasNext()` method, returns a boolean value that indicates whether or not there are more elements to be traversed. The `next()` method, returns the next element in the traversal. 

A collection can have more than one `Iterator`s, if it makes sense for the collection to be travsersed in more than one way. Despite this possibility, a collection must have a default iterator which will be the type of the new instance returned in the factory method, `newIterator()`

> Note: `MyBTree` and its iterator is optional

**Complete the system using the iterator pattern**

## Assessment Criteria

- Completeness of the pattern - 40
- Elegance of method and attribute naming - 10

**Deadline November 30, 2020**