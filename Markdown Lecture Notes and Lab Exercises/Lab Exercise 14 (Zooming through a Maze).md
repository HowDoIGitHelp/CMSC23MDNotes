# Lab Exercise 14 (Zooming through a Maze)

## Task

You're creating a maze navigation game thing. This is what the application currently has right now:

- `Board` - this represents the layout of the maze. The layout is loaded from a file. It has these attributes:
  - `isSolid: List<List<Boolean>>` - this is a 2 dimensional grid encoded as a nested list of booleans which represents the solid boundaries of the maze. For example if `__isSolid[row][col]` is true then it means that that cell on (row,col) is a boundary
  - `start: Pair<Int,Int>` - a pair of integers that represent where the character starts
  - `end: Pair<Int,Int>`: - a pair of integers that represent the position of the end of the maze
  - `cLoc: Pair<Int,Int>` - a pair of integers that represents the current location of the character. This is a private attribute but you can return a copy of this attribute using `characterLocation(): Pair<Int,Int>`
  - `moveUp()`, `moveDown()`, `moveLeft()`, `moveRight()` - moves the character one space, in the respective direction. The character cannot move to a boundary cell, it will raise a `BoundaryCollisionError` instead.
  - `canMoveUp(): Boolean`, `canMoveDown(): Boolean`, `canMoveLeft(): Boolean`, `canMoveRight(): Boolean` - returns true if the cell in the respective direction is not solid.
  - `teleportCharacter(newLocation: Pair<Int, Int>)` - teleports the character to a new location. If the new location passed is invalid then it will throw a `BoundaryCollisionError`.
  - `toString(): String` string representation of the board. It shows which are the boundaries and the character location

What you need to add:

- `Controller` - this controls the character movement. 
  - `pressUp()`, `pressDown()`, `pressLeft()`, `pressRight()` - The character dashes through the maze in the specified direction **until it hits a boundary**.
  - `undo()` -  The character undoes the previous action performed

- `Command` and its realizations: `DashUpCommand`, `DashDownCommand`, `DashLeftCommand`, `DashRightCommand`. These commands must be undoable, through controller.

What is missing in this system are the actual commands and command realizations.

![command example](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/commandexample.png)

## Assessment Criteria

- Completeness of the pattern - 40

  
