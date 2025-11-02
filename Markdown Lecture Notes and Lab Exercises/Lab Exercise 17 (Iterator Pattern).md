# Lab Exercise 17 (Iterator Pattern)

## Task

For non built-in collections, you can create an iterator that does the traversal for you. On the bare minimum these iterators will realize some `Iterator` abstraction that contains the methods, `next()`, and `hasNext()`. From these methods alone you can easily perform complete traversals without knowing the exact type of the collection:

```python
i = collection.newIterator()
while i.hasNext():
	print(i.next())
```

![iterator](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/umlOutputs/Iterator.svg)

The `hasNext()` method, returns a boolean value that indicates whether or not there are more elements to be traversed. The `next()` method, returns the next element in the traversal. 

A collection can have more than one `Iterator`s, if it makes sense for the collection to be traversed in more than one way. Despite this possibility, a collection must have a default iterator which will be the type of the new instance returned in the factory method, `newIterator()`

> Note: `MyBTree` and its iterator is optional

**Complete the system using the iterator pattern**

## Assessment Criteria

- Completeness of the pattern - 40