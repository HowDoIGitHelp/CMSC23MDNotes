# Lab Exercise 11 (States of Matter)

## Task

The state of any given matter is dependent on the pressure and temperature of its environment. If you heat up some liquid enough it will turn to gas, if you compress it enough it will become solid.

```python
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

```

You are to build a less sophisticated version of this model in code. Matter comes in three states, solid, liquid, and gas. The state of the matter may change if you put/remove pressure on it or heat/cool it.

The state diagram would look something like this:

![state diagram example](C:/Users/rrabe/Google Drive/Lecture-Notes-And-Resources/CMSC 23/uml/statediagramexample.png)

To implement something like this, you would need to create matter which owns an attribute called `state` which represents the matter's current state. Since there are three states, you create three realizations to a common abstraction to state. 

When you compress/release/cool/heat the matter, you delegate the appropriate behavior and state change inside `state`'s version of that. Each `State` realization will need a backreference to the `Matter` that owns it so that it can change it's state.

> Delegating behavior to the composed state means that, when the `Matter` instances invoke, `compress()`, `relaease()` `heat()`, and `cool()`, the composed `State` owned by the matter calls its own version of `compress()`, `relaease()` `heat()`, and `cool()`.

![state example](C:/Users/rrabe/Google Drive/Lecture-Notes-And-Resources/CMSC 23/uml/stateexample.png)

> Matter owns an instance of `State`, and that instance has an attribute called `matter`. The attribute `matter`  is the reference to the instance of `Matter` that owns it. The `State` instance needs this reference so that it can change the matter's state when it is compressed, released, heated, or cooled.

**Complete the system using the state pattern.**

## Assessment Criteria

- Completeness of the pattern - 40
- Elegance of method and attribute naming - 10

**Deadline November 30, 2020**