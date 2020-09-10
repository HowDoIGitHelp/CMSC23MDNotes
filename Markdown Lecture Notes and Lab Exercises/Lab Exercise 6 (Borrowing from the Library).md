# Lab Exercise 6 (Borrowing from the Library)

## Task

You are to implement the following system. This system represents the borrowing and returning functions of a library. Here's the class diagram. **Edit the python file that came with this document.** The edited file is what you are going to submit.

![UML](https://i.imgur.com/9zHBavi.png).

There is some code already written for you here:

```python
from abc import ABC, abstractmethod

class Date:
    def __init__(self, month:int, day:int, year:int):
        self.__month = month
        self.__day = day
        self.__year = year
    def mdyFormat(self) -> str:
        return str(self.__month) + "/" + str(self.__day) + "/" + str(self.__year)
    
class Page:
    def __init__(self, sectionHeader:str, body: str):
        self.__sectionHeader = sectionHeader
        self.__body = body
        
class BorrowableItem(ABC):
    @abstractmethod
    def uniqueItemId(self) -> int:  
        pass
    @abstractmethod
    def commonName(self) -> str:
        pass
    
    
        
class Book(BorrowableItem):
    def __init__(self, bookId:int, title:str, author:str, publishDate:Date, pages: [Page]):
        self.__bookId = bookId
        self.__title = title
        self.__publishDate = publishDate
        self.__author = author
        self.__pages = pages
    def coverInfo(self) -> str:
        return "Title: " + self.__title + "\nAuthor: " + self.__author
    def uniqueItemId(self) -> int:
        return self.__bookId
    def commonName(self) -> str:
        return "Borrowed Item:" + self.__title + " by " + self.__author
    

class LibraryCard:
    def __init__(self, idNumber: int, name: str, borrowedItems: {BorrowableItem:Date}):
        self.__idNumber = idNumber
        self.__name = name
        self.__borrowedItems = borrowedItems
    def borrowItem(self,book:BorrowableItem, date:Date):
        self.__borrowedItems[book] = date
    def borrowerReport(self) -> str:
        r:str = self.__name + "\n"
        for borrowedItem in self.__borrowedItems:
            r = r + borrowedItem.commonName() + ", borrow date:" + self.__borrowedItems[borrowedItem].mdyFormat() + "\n"
        return r
```

Creating an instance of a `BorrowableItem` (in this case an instance of the particular realization, `Book`) is done using the following code.

```python
b:BorrowableItem = Book(10991,"Corpus Hermeticum", "Hermes Trismegistus", Date(9,1,1991), [])
print(b.commonName()) #commonName() returns the string representation of a borrowable item
```

Creating an instance of a `LibraryCard` is done using the following.

```python
l:LibraryCard = LibraryCard(9982,"Rubelito Abella",{})
```

A library card borrows something using the `borrowItem(BorrowableItem)` method. And the `borrowerReport()` prints the library card owners name and the items he/she has borrowed.

```python
l.borrowItem(b,Date(9,25,2019))
print(l.borrowerReport())
```

### What you should do:

#### The class definitions above are still missing `Periodical` and `PC`. 
 - a **`Periodical`** represents a periodical (newspaper, magazines, etc). It is a realization of a `BorrowableItem`. It contains the following methods and attributes:
      - `__init__`: initializes a periodical instance with the attributes `__periodicalID:int`, `__title:str`, `__title:str`
     - `__periodicalID:int`: unique id for a periodical
     - `__title:str`: The title of the periodical ("National Geographic", "New York Times")
     - `__issue:Date`: The date when the issue was published
     - `__pages:[Page]`: A list of `Page`s that represent the contents
     - `uniqueItemId()`: Returns `periodicalID`
     - `commonName():str`: (Implementation of the abstract method from `BorrowableItem`). It returns the title and the issue date in month/date/year format as a string for example "National Geographic issue: 4/6/2001")
 - a **`PC`** represents a library PC. It is a realization of a `BorrowableItem`.  It contains the following methods and attributes:
      - `__init__`: initializes a PC instance with the attribute `__pcID:int`.
     - `__pcID:int` : unique id for a PC
     - `uniqueItemId():int`: Returns `__pcID`
     - `commonName():str`: (Implementation of the abstract method from `BorrowableItem`). It returns "PC<_\_pcID>" (the string "PC" followed by the value attribute `__pcID`, for example "PC1342")

#### Add the following methods to `LibraryCard` and implement them
 - **`returnItem(b:BorrowableItem):`** : returns nothing, it removes the `BorrowableItem`, `b` from the `__borrowedItems` dictionary.
 - **`penalty(b:BorrowableItem,today:Date):float`** : returns a float which is the calculated penalty for `BorrowableItem`, `b` when returned today. Every day after the due date the penalty increases by 3.5. An item which is overdue for 4 days has a penalty of 14. 
 - **`itemsDue(today:Date):[BorrowableItem]`** : returns a list of `BorrowableItem`s which are on or past the due date. The due date for a `Book` is 7 days, a `Periodical` is 1 day, and a `PC` is 0 days.
 - **`totalPenalty(today:Date):float`** : returns a float which is the total penalty for all the overdue items when calculated today

Feel free (in fact you are encouraged) to add extra methods to any of the classes above that will help you in implementing the whole system. Just make sure the extra methods don't unnecessarily expose hidden attributes.

## Assessment Criteria

- Completeness of `BorrowableItems` attributes and methods - 20
- Completeness of `LibraryCard` methods - 20
- Correctness of attribute and methods names - 10

**Deadline November 30, 2020**