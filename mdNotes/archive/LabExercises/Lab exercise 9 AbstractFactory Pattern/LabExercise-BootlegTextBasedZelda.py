from random import randint

class NormalBokoblin:
    def bludgeon(self):
        print("Bokoblin bludgeons you with a boko club for 1 damage")
    def defend(self):
        print("Bokoblin defends itself with a boko shield")
    def announce(self):
        print("A bokoblin appeared")
    def move(self):
        if randint(1,3) > 1:
            self.bludgeon()
        else:
            self.defend()

class NormalMoblin:
    def stab(self):
        print("Moblin stabs you with a spear for 3 damage")
    def kick(self):
        print("Moblin kicks you for 1 damage")
    def announce(self):
        print("A moblin appeared")
    def move(self):
        if randint(1,3) > 1:
            self.stab()
        else:
            self.kick()

class NormalLizalflos:
    def throwBoomerang(self):
        print("Lizalflos throws its lizal boomerang at you for 2 damage")
    def hide(self):
        print("Lizalflos camouflages itself")
    def announce(self):
        print("A lizalflos appeared")
    def move(self):
        if randint(1,3) > 1:
            self.throwBoomerang()
        else:
            self.hide()



class Encounter:
    def __init__(self):
        self.__enemies = []
        for i in range(randint(0,8)):
            r = randint(1,3)
            if r == 1:
                self.__enemies.append(NormalBokoblin())
            elif r==2:
                self.__enemies.append(NormalMoblin())
            else:
                self.__enemies.append(NormalLizalflos())

    def announceEnemies(self):
        print("%d monsters appeared" % len(self.__enemies))
        for enemy in self.__enemies:
            enemy.announce()

    def moveEnemies(self):
        for enemy in self.__enemies:
            enemy.move()



encounter = Encounter()
encounter.announceEnemies()
print()
encounter.moveEnemies()
