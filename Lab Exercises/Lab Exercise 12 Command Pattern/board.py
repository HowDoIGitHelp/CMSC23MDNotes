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
