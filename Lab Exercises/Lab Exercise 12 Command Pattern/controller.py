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
"""
from msvcrt import getch
while True:
    key = ord(getch())
    if key == 27: #ESC
        break
    elif key == 122: #Z key
        c.undo()
    elif key == 224: #Special keys (arrows, f keys, ins, del, etc.)
        key = ord(getch())
        if key == 80: #Down arrow
            c.pressDown()
        elif key == 72: #Up arrow
            c.pressUp()
        elif key == 75: #Left arrow
            c.pressLeft()
        elif key == 77: #Up arrow
            c.pressRight()
    print(b)
"""
