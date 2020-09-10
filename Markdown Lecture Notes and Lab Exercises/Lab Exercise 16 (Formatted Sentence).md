# Lab Exercise 16 (Formatted Sentence)

## Task

A sentence can be defined as a list of words (words are strings). The string representation of a sentence is the concatenation of all of the words in the list, separated by a space.

```python
from abc import ABC,abstractmethod

class Sentence:
    def __init__(self,words:[str]):
        self.__words = words

    def __str__(self) -> str:
        sentenceString = ""
        for word in self.__words:
            sentenceString += word + " "
        return sentenceString[:-1]

```

Instances of sentences can be printed with formatting:

- **bordered** - Given the sentence, `["hey","there"]` it prints:

  ```
  -----------
  |hey there|
  -----------
  ```

- **fancy** - Given the sentence, `["hey","there"]` it prints:

  ```
  -+hey there+-
  ```

- **uppercase** - Given the sentence, `["hey","there"]` it prints:

  ```
  HEY THERE
  ```

The formatting of a sentence is decided during runtime. These formats should also allow for combinations with other formats:

- **bordered fancy** - Given the sentence, `["hey","there"]` it prints:

  ```
  ---------------
  |-+hey there+-|
  ---------------
  ```

- **fancy uppercase** - Given the sentence, `["hey","there"]` it prints:

  ```
  -+HEY THERE+-
  ```

To accomplish these features, you need to implement the decorator pattern. Each formatting will be a decorator for `Sentence` objects. These formats need to inherit from some abstract `FormattedSentence` class. This abstract class is specified to compose and inherit from sentence. The behavior that needs to be decorated is the `__str__()` function since you need to change how sentence is printed for every format.

![decorator example](C:/Users/rrabe/Google Drive/Lecture-Notes-And-Resources/CMSC 23/uml/decoratorexample.png)

**Complete the system using the decorator pattern**

## Assessment Criteria

- Completeness of the pattern - 40
- Elegance of method and attribute naming - 10

**Deadline November 30, 2020**