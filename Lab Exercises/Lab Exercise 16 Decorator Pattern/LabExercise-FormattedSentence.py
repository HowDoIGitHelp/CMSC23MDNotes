from abc import ABC,abstractmethod

class Sentence:
    def __init__(self,words:[str]):
        self.__words = words

    def __str__(self) -> str:
        sentenceString = ""
        for word in self.__words:
            sentenceString += word + " "
        return sentenceString[:-1]
