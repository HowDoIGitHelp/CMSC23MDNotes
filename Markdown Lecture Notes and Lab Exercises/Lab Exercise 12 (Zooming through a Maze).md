# Lab Exercise 12 (Zooming through a Maze)

## Task

You're creating a maze navigation game thing. This is what the application currently has right now:

- `Board` - this represents the layout of the maze. The layout is loaded from a file. It has these attributes:
  - `__isSolid` - this is a 2 dimensional grid encoded as a nested list of booleans which represents the solid boundaries of the maze. For example if `__isSolid[row][col]` is true then it means that that cell on (row,col) is a boundary
  - `__start` - a tuple of two integers that represent where the character starts
  - `__end` - tuple of two integers that represent the position of the end of the maze
  - `__cLoc` - tuple of two integers that represents the current location of the character
  - `moveUp()`, `moveDown()`, `moveLeft()`, `moveRight()` - moves the character one space, in the respective direction. The character cannot move to a boundary cell, it will raise an error instead.
  - `canMoveUp()`, `canMoveDown()`, `canMoveLeft()`, `canMoveRight()` - returns true if the cell in the respective direction is not solid.
  - `__str()__` string representation of the board. It shows which are the boundaries and the character location

**Board**

```python
class BoundaryCollisionError(Exception):
    def __init__(self,point):
        self.collidingBoundary = point

class Board:
    def __init__(self,filename:str="boardFile.py"):
        self.__isSolid = []
        with open(filename,"r") as f:
            self.__start = tuple(map(int,f.readline().split()))
            self.__end = tuple(map(int,f.readline().split()))
            rawcontents = f.readlines()

            for line in rawcontents:
                self.__isSolid.append(list(map((lambda x: x=="#"), line[:-1])))

        self.__cLoc = self.__start



    def characterLocation(self):
        return self.__cLoc

    def moveRight(self):
        (row,col) = self.__cLoc
        try:
            if self.__isSolid[row][col+1]:
                raise BoundaryCollisionError((row,col+1))
            else:
                self.__cLoc = (row,col+1)
        except IndexError:
            raise BoundaryCollisionError((row,col+1))

    def moveDown(self):
        (row,col) = self.__cLoc
        try:
            if self.__isSolid[row+1][col]:
                raise BoundaryCollisionError((row+1,col))
            else:
                self.__cLoc = (row+1,col)
        except IndexError:
            raise BoundaryCollisionError((row+1,col))

    def moveUp(self):
        (row,col) = self.__cLoc
        try:
            if self.__isSolid[row-1][col]:
                raise BoundaryCollisionError((row-1,col))
            else:
                self.__cLoc = (row-1,col)
        except IndexError:
            raise BoundaryCollisionError((row-1,col))

    def moveLeft(self):
        (row,col) = self.__cLoc
        try:
            if self.__isSolid[row][col-1]:
                raise BoundaryCollisionError((row,col-1))
            else:
                self.__cLoc = (row,col-1)
        except IndexError:
            raise BoundaryCollisionError((row,col-1))

    def canMoveUp(self) -> bool:
        (row,col) = self.__cLoc
        try:
            if self.__isSolid[row-1][col]:
                return False
            else:
                return True
        except IndexError:
            return False

    def canMoveDown(self) -> bool:
        (row,col) = self.__cLoc
        try:
            if self.__isSolid[row+1][col]:
                return False
            else:
                return True
        except IndexError:
            return False

    def canMoveRight(self) -> bool:
        (row,col) = self.__cLoc
        try:
            if self.__isSolid[row][col+1]:
                return False
            else:
                return True
        except IndexError:
            return False

    def canMoveLeft(self) -> bool:
        (row,col) = self.__cLoc
        try:
            if self.__isSolid[row][col-1]:
                return False
            else:
                return True
        except IndexError:
            return False


    def __str__(self):
        mapString = ""
        for row in range(len(self.__isSolid)):
            for col in range(len(self.__isSolid[0])):
                if ((row,col) == self.__start or (row,col) == self.__end) and (row,col) != self.__cLoc:
                    mapString += "o"
                elif (row,col) == self.__cLoc:
                    mapString += "+"
                elif self.__isSolid[row][col]:
                    mapString += "#"
                else:
                    mapString += "."
            mapString += "\n"
        return mapString

    def teleportCharacter(self,newLocation):
        (row,col) = newLocation
        if self.__isSolid[row][col]:
            raise BoundaryCollisionError((row,col))
        else:
            self.__cLoc = newLocation



#to test if board works, uncomment the following
#b = Board("boardfile.in")
#print(b)
#b.moveRight()
#print(b)
```

**Controller**

```python
from board import Board
from commands import DashUpCommand,DashLeftCommand,DashDownCommand,DashRightCommand

class Controller:
    def __init__(self,board:Board):
        self.__board = board
        self.__commandHistory = []

    def pressUp(self):
        command = DashUpCommand(self.__board)
        self.__commandHistory.append(command)
        command.execute()

    def pressDown(self):
        command = DashDownCommand(self.__board)
        self.__commandHistory.append(command)
        command.execute()

    def pressLeft(self):
        command = DashLeftCommand(self.__board)
        self.__commandHistory.append(command)
        command.execute()

    def pressRight(self):
        command = DashRightCommand(self.__board)
        self.__commandHistory.append(command)
        command.execute()

    def undo(self):
        undoneCommand = self.__commandHistory.pop()
        undoneCommand.undo()



b = Board("boardFile.in")
c = Controller(b)
print(b)
c.pressRight()
print(b)
c.pressDown()
print(b)
```

What's missing right now is controller support. This is how a player controls the character on the maze:

- `dpad_up()`, `dpad_down()`, `dpad_left()`, `dpad_right()` - The character dashes through the maze in the specified direction until it hits a boundary. (these methods are equivalent to `pressUp()`, `pressDown()`, and etc on the `Controller`)
- `a_button()` -  The character undoes the previous action it did. (this is equivalent to `undo()` on `Controller`)

To implement controller support you need to create a `Command` abstraction which is realized by all controller commands. The `Controller` (which represents the controller) is the invoker for the commands. Since commands are undoable, this controller needs to keep a command history, represented as a list. Every time a controller button is pressed, it creates the appropriate `Command`, executes it and appends it to the command history. Every time the `a_button()` is pressed to undo, the controller pops the last command from the command history and undoes it.

![command example](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/commandexample.png)

> `backupLocation : (Int,Int)` is an attribute but the diagram shows it as a method. It's supposed to be a private attribute of type `(Int,Int)` tuple but planUML (the uml renderer im using) reads it as a tuple because of the tuple parentheses

**Complete the system using the command pattern**

## Assessment Criteria

- Completeness of the pattern - 40
- Elegance of method and attribute naming - 10
