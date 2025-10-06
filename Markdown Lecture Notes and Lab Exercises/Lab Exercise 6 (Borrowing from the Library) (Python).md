# Lab Exercise 6 (Borrowing from the Library) (Python)

## Task

You are to implement the following system. This system represents the borrowing and returning functions of a library. Here's the class diagram. **Edit the file that came with this document.** The edited file is what you are going to submit.

![UML](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/706f15bf8a0c7ed2267233efbdb3d89e918175ad/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/umlOutputs/BooksBorrowing.svg)

Here are the classes currently available in the existing code base:

### BorrowableItem 

A borrowable item is something from the library that can be borrowed. It is an interface containing the following abstract functions:

- `uniqueItemId(): int` - it should return a unique identifier for the specific `BorrowableItem` instance
- `commonName(): string` - a string representation of the common name of a given `BorrowableItem` instance

### Book

A realization of `BorrowableItem`. It represents a book. `Book` instances can be constructed using its following primary constructor (refer to the uml for the primary constructor pattern).

 * `bookId` - the unique identifier for the book, 
 * `title` - the title of the book 
 * `author` - the author of the book
 * `publishDate` - the date this book was published
 * `pages` - a list of `Pages`. Represents the books contents

### Pages

A simple data holder that represents a page in a book. 

### LibraryCard

Represents a Library Card. Contains borrower information

- `idNumber` - unique identifier for the borrower
- `name` - name of the borrower
- `borrowedItems` - a mutable map containing `BorrowedItem` instances mapped to the said items borrow date. The borrow date are instances of `date` from `java.time.LocalDate`
- `borrowItem(item: BorrowableItem, date: date)` - is invoked to borrow a borrowable item from the library. Invoking this function adds a new entry in `borrowedItems`
- `borrowerReport(): string` - returns a borrower report which includes the name and with a list of borrowed items along with each item's borrow date.

## What you should do:

The class definitions above are still missing `Periodical` and `PC`.  Refer to the uml for visibility modifiers

### Periodical

a **`Periodical`** represents a periodical (newspaper, magazines, etc). It is a realization of a `BorrowableItem`. It contains the following methods and attributes:

- `Periodical(periodicalId: int, title: string, issue: date, pages: list[Page])` 
- `issue: date` - represents the publish date of the `Periodical` instance
- `pages: list[Page]` - represents a the contents of the `Periodical` instance
- `periodicalId: int` - the unique id of this periodical instance
- `title: string` - The title of the periodical ("National Geographic", "New York Times")
- `commonName(): string` - (Implementation of the abstract method from `BorrowableItem`). It returns the title and the issue date in year-month-day format as a string for example "National Geographic issue: 2001-4-6")
- `uniqueItemId(): int` - Returns `periodicalID`

### PC

- `PC(pcID: int)` - initializes a `PC` instance
- `pcID: int` - unique id for a PC
- `commonName(): string` - (Implementation of the abstract method from `BorrowableItem`). It returns "PC<pcID>" (the string "PC" followed by the value attribute `pcID`, for example "PC1342")
- `uniqueItemId(): int` - Returns `pcID`

#### Implement the following methods to `LibraryCard`
- **`returnItem(b: BorrowableItem):`** : returns nothing, it removes the `BorrowableItem`, `b` from the `borrowedItems` dictionary.
- **`penalty(b: BorrowableItem,today: date): float`** : returns a float which is the calculated penalty for `BorrowableItem`, `b` when returned today. Every day after the due date the penalty increases by 3.5. An item which is overdue for 4 days has a penalty of 14. 
- **`itemsDue(today: date): list[BorrowableItem]`** : returns a list of `BorrowableItem`s which are on or past the due date. The due date for a `Book` is 7 days, a `Periodical` is 1 day, and a `PC` is 0 days.
- **`totalPenalty(today: date): float`** : returns a `float` which is the total penalty for all the overdue items when calculated today.

> The parameter `today: date` represents the date today. For example `itemsDue(today: date)` will return a list of `BorrowableItem`s past the due date if if the date was `today`.

Feel free (in fact you are encouraged) to add extra methods to any of the classes above that will help you in implementing the whole system. Just make sure the extra methods don't unnecessarily expose hidden attributes.

## Assessment Criteria

- Completeness of `BorrowableItems` attributes and methods - 20
- Completeness of `LibraryCard` methods - 20
