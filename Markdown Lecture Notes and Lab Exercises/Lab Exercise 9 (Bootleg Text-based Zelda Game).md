# Lab Exercise 9 (Bootleg Text-based Zelda Game)

## Task

You're creating the dungeon encounter mechanics of some bootleg text-based zelda game. In this game,every time you enter a dungeon, you encounter 0-8 monsters (the exact number is randomly determined). There are 3 types of monsters, bokoblins, moblins, and lizalflos (different types have different moves). The exact type of monster is randomly decided as well. 

```python
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

```

Right now the game works like this:

As soon as you enter the dungeon, all the enemies are announced:

```
5 monsters appeared
A lizalflos appeared
A lizalflos appeared
A lizalflos appeared
A moblin appeared
A moblin appeared
```

After this, each enemy in the encounter attacks. They randomly pick an attack from their moveset.

 ```
Lizalflos thorws its lizal boomerang at you for 2 damage
Lizalflos thorws its lizal boomerang at you for 2 damage
Lizalflos camouflages itself
Moblin stabs you with a spear for 3 damage
Moblin stabs you with a spear for 3 damage
 ```

The encounter ends with Link dying since you haven't coded anything past this part.

You decide to make things exciting for your game by adding harder dungeons, medium dungeon and hard dungeon.

##### Medium dungeon

Instead of encountering, normal monsters you encounter stronger versions of the monsters, these monsters are blue colored:

- **Blue Bokoblin** 
  - equipped with a spiked boko club and a spiked boko shield
  - bludgeon deals 2 damage
- **Blue Moblin**
  - equipped with rusty halberd
  - stab deals 5 damage
  - kick deals 2 damage
- **Blue Lizalflos**
  - equipped with a forked boomerang
  - throw boomerang deals 3 damage

##### Hard dungeon

These monsters are silver colored extra stronger versions of the monsters

- **Silver Bokoblin** 
  - equipped with a dragonbone boko club and a dragonbone boko shield
  - bludgeon deals 5 damage
- **Silver Moblin**
  - equipped with knight's halberd
  - stab deals 10 damage
  - kick deals 3 damage
- **Silver Lizalflos**
  - equipped with a tri-boomerang
  - throw boomerang deals 7 damage

To seamlessly incorporate these harder monsters in your system, you need to create an abstract factory for each dungeon difficulty.  There are now three variants for each monster. For every variant, there is a factory that spawns new instances of each monster. **Complete the system using the abstract factory pattern.**

![abstract factory example](C:/Users/rrabe/Google Drive/Lecture-Notes-And-Resources/CMSC 23/uml/abstractFactoryExample.png)

## Assessment Criteria

## Assessment Criteria

- Completeness of the pattern - 40
- Elegance of method and attribute naming - 10

**Deadline November 30, 2020**