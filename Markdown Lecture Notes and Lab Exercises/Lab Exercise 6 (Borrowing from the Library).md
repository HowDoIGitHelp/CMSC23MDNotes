# Lab Exercise 6 (Borrowing from the Library)

## Task

You are to implement the following system. This system represents the borrowing and returning functions of a library. Here's the class diagram. **Edit the python file that came with this document.** The edited file is what you are going to submit.

![UML](https://i.imgur.com/9zHBavi.png)

There is some code already written for you here:

```python
from abc import ABC, abstractmethod
from datetime import date,timedelta

def daysBetween(date1:date, date2:date) -> int:
    difference:int = date1 -  date2
    return difference.days

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
    def __init__(self, bookId:int, title:str, author:str, publishDate:date, pages: list[Page]):
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
    def __init__(self, idNumber: int, name: str, borrowedItems: dict[BorrowableItem,date]):
        self.__idNumber = idNumber
        self.__name = name
        self.__borrowedItems = borrowedItems
    def borrowItem(self,item:BorrowableItem, date:date):
        self.__borrowedItems[item] = date
    def borrowerReport(self) -> str:
        r:str = self.__name + "\n"
        for borrowedItem in self.__borrowedItems:
            r = r + borrowedItem.commonName() + ", borrow date:" + str(self.__borrowedItems[borrowedItem]) + "\n"
        return r
```

Creating an instance of a `BorrowableItem` (in this case an instance of the particular realization, `Book`) is done using the following code.

```python
b:BorrowableItem = Book(10991,"Corpus Hermeticum", "Hermes Trismegistus", date(1991,1,9), [])
print(b.commonName()) #commonName() returns the string representation of a borrowable item
```

Creating an instance of a `LibraryCard` is done using the following.

```python
l:LibraryCard = LibraryCard(9982,"Rubelito Abella",{})
```

A library card instance borrows something using the `borrowItem(item:BorrowableItem, date:date)` method. Inside the method, a new entry in the dictionary is created, with `item` as the key and `date` as the borrow date. The method `borrowerReport()` prints the library card owners name and the items he/she has borrowed. 

```python
l.borrowItem(b,date(2019,9,25))
print(l.borrowerReport())
```
```
Rubelito Abella
Borrowed Item:Corpus Hermeticum by Hermes Trismegistus, borrow date:2019-09-25
```

### Some notes on the type annotations
- `date` - is a type from the `datetime` library from python. You can see the library import above the code. In this system we use `date` to represent dates and calculate `daysBetween`.
- `dict[BorrowableItem,date]` - this is the type of `LibraryCard`'s attribute:`__borrowedItem`. It is a dictionary with `BorrowableItem`s as the key and `date`s as the **borrow date** (not the due date) of the associated key. Please refer to [Python Introduction](https://hackmd.io/@RubAbella/Syz0e_k8B) for more info on dictionaries.

### What you should do:

#### The class definitions above are still missing `Periodical` and `PC`. 
 - a **`Periodical`** represents a periodical (newspaper, magazines, etc). It is a realization of a `BorrowableItem`. It contains the following methods and attributes:
      - `__init__`: initializes a periodical instance with the attributes `__periodicalID:int`, `__title:str`, `__issue:date`, `__pages:list[Page]`
     - `__periodicalID:int`: unique id for a periodical
     - `__title:str`: The title of the periodical ("National Geographic", "New York Times")
     - `__issue:date`: The date when the issue was published
     - `__pages:list[Page]`: A list of `Page`s that represent the contents
     - `uniqueItemId()`: Returns `periodicalID`
     - `commonName():str`: (Implementation of the abstract method from `BorrowableItem`). It returns the title and the issue date in year-month-day format as a string for example "National Geographic issue: 2001-4-6")
 - a **`PC`** represents a library PC. It is a realization of a `BorrowableItem`.  It contains the following methods and attributes:
      - `__init__`: initializes a PC instance with the attribute `__pcID:int`.
     - `__pcID:int` : unique id for a PC
     - `uniqueItemId():int`: Returns `__pcID`
     - `commonName():str`: (Implementation of the abstract method from `BorrowableItem`). It returns "PC<_\_pcID>" (the string "PC" followed by the value attribute `__pcID`, for example "PC1342")

#### Add the following methods to `LibraryCard` and implement them
 - **`returnItem(b:BorrowableItem):`** : returns nothing, it removes the `BorrowableItem`, `b` from the `__borrowedItems` dictionary.
 - **`penalty(b:BorrowableItem,today:date):float`** : returns a float which is the calculated penalty for `BorrowableItem`, `b` when returned today. Every day after the due date the penalty increases by 3.5. An item which is overdue for 4 days has a penalty of 14. 
 - **`itemsDue(today:date):list[BorrowableItem]`** : returns a list of `BorrowableItem`s which are on or past the due date. The due date for a `Book` is 7 days, a `Periodical` is 1 day, and a `PC` is 0 days.
 - **`totalPenalty(today:date):float`** : returns a float which is the total penalty for all the overdue items when calculated today.

> The parameter `today:date` represents the date today. For example `itemsDue(today:date)` will return a list of `BorrowableItem`s past the due date if if the date was `today`.

Feel free (in fact you are encouraged) to add extra methods to any of the classes above that will help you in implementing the whole system. Just make sure the extra methods don't unnecessarily expose hidden attributes.

### Self-testing
Test your code using the included `tests.py` file. First make sure that the file name of the python script containing your system is "`lab6.py`". Make sure that both `tests.py` and `lab6.py` are on the same library. The aforementioned steps are necessary so that `tests.py` successfully imports the classes and functions of `lab6.py`.

Run `tests.py` using the command `python tests.py`. Running `tests.py` on your IDE should also work. If your code passes the test cases, you should find the following results:
```
> python tests.py
```
```
polymorphism test:
OK
returning items test:
OK
penalty test:
OK
total penalty test:
OK
```

## Assessment Criteria

- Completeness of `BorrowableItems` attributes and methods - 20
- Completeness of `LibraryCard` methods - 20
- Correctness of attribute and methods names - 10