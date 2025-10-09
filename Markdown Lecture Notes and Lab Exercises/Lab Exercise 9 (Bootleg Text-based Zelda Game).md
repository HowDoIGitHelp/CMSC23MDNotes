# Lab Exercise 9 (Bootleg Text-based Zelda Game)

You're creating the dungeon encounter mechanics of some bootleg text-based zelda game. In this game,every time you enter a dungeon, you encounter 0-8 monsters (the exact number is randomly determined). There are 3 types of monsters, bokoblins, moblins, and lizalflos (different types have different moves). The exact type of monster is randomly decided as well. 

**Right now the game works like this:**

![abstract factory example](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/umlOutputs/TextBasedZeldaNoAbstractFactory.svg)

When you enter a dungeon an `Encounter` instance is constructed. The encounter instance contains, a 0-8 random enemies. Using the newly created `Encounter` instance, `announceEnemies()` is invoked. This displays all the enemies in the encounter.

```
5 monsters appeared
A lizalflos appeared
A lizalflos appeared
A lizalflos appeared
A moblin appeared
A moblin appeared
```

After this, the encounter instance's `moveEnemies()` method is invoked. Using this, each enemy in the encounter attacks. During each attack, each enemy will choose a random move from their moveset.

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

![abstract factory example](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/umlOutputs/TextBasedZelda.svg)

## Assessment Criteria

- Correct `EasyDungeon` behavior - 5
- Correct `MediumDungeon` behavior - 5
- Correct `HardDungeon` behavior - 5
- Correct class structure - 15
- Class architecture supports extensibility -10