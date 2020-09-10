# Class Relationships

## Introduction

The interactions between one an instance of a class to another is largely characterized by the relationship between them. Here we talk about relationships that define the polymorphism between classes and relationships that define dependency between objects.

## Learning Outcomes

1. Differentiate realization relationships and specialization relationships
2. Describe how class abstract methods work in realization relationships
3. Describe the concept of inheritance
4. Differentiate aggregation relationships and composition relationships

---

## Type Based Relationships

Type based relationships are characterized by how two classes are related to each other through ontological hierarchy. A mammal class's relationship with an animal class's relationship is type based. That is because a mammal is considered as an animal while an animal is not necessarily a mammal. 

There are two type based relationships (there can be an extra one which is a type relationship that is sort of a hybrid of the two).

### Realization

A realization relationship is a one way relationship that describes how something abstract is REALized by something concrete. Given a `Realization` class and an `Abstraction` class, The `Realization` class realizes the `Abstraction` class. 

> A realization relationship is also called an **implementation** relationship. An implementation, implements some interface. **Interface** is also a fitting term for abstractions because it is through these classes that other objects interact with each other.

An `Abstraction` is a special type of class that does not contain any implementation. This means that `Abstraction` doesn't have code that controls the form and behavior of the class. It only contains code that specify how this class interacts with other objects. This means that abstract classes only contain method names and type signatures with empty bodies.

These `Abstraction` classes appear useless at first since it doesn't do anything at all. In fact you cannot even create an instance of an abstraction. Even if you do it will be pointless since it doesn't have code that controls how it behaves. 

An abstraction can only be useful if some other class realizes this abstraction. These `Realization` classes provide abstractions their form and behavior. 

**What's the point in maintaining some realization relationship between classes? If abstractions can only be used through their realizations , then why create the abstraction at all?**

The importance of this seemingly pointless relationship lies in OOP's data hiding principle. We will explore more about why these relationships are very common in a future discussion about SOLID principles. For now I'll show one of the reasons why this is useful through an example:

![Realization Relationship Example](custom art\RealizationRelationship.png)

Consider a library system. In a library, you are able to borrow resources such as books, newspapers, and computers. When the library system interacts with these resources to facilitate transactions such as borrowing and returning, the library system treats these resource like general **Borrowable Items**. It doesn't really need to concern itself of the specific type. Operating like this is better for the library system for reasons such as maintainability and future proofing (this will be explained in detail in when we discuss SOLID design principles). Therefore, the best architecture to use in this situation is to make each resource type a realization of Borrowable Item. A `BorrowableItem` class would merely be an abstraction. This class would contain no behavior or form, it only contains specifications of how to interact with it. And it does make sense, I mean, how exactly does a general borrowable item behave or look like? It's abstract. What we know is that any `BorrowableItem` can be borrowed or returned so we write empty `borrow()` and `return()` functions (it specifies how it interacts with others but it doesn't have any specific behavior, these methods are called abstract methods).

Any realization of `BorrowableItem`, such as `Book` or `Newspaper` will be forced to implement the `borrow()` and `return()` methods as well (btw all realizations are forced to implement the methods of its abstraction), meaning it needs to include these methods with each of their own method bodies for borrowing and returning (if books and newspapers are borrowed or returned in different manners, then you write different method bodies for each).

> Realizations can also have extra methods that are not present in the abstractions.

This is how realization relationships enable **polymorphism**, a `Book` is a `BorrowableItem`, allowing the library system to interact with it like any `BorrowableItem`. But at the same time `Book` is a book so it behaves in the manner a book behaves.

By building all of these relationships, the library system is able interact with resources without explicitly knowing which exact resource it is. The system knows that it is borrowing some instance of a borrowable item but it does not know if it is a book or a newspaper. The exact type of this instance will then behave depending on its type. Although all this effort may seem unnecessary, you will learn in this course that through the establishment of these relationships, OOP is able to uphold one of its core design principles, **data-hiding**.

Abstractions can also realize abstractions. For example given an abstraction A, another abstraction, called B, can realize A. A class C then realizes B. When this happens, B does not need to implement A's abstract methods because B is an abstraction itself. Therefore, C will inherit all of A and B's abstract methods. The abstract methods of an abstraction cascades down to the realization realizing any of its realizations as well. 

In this case C instances can be treated as B instances or A instances but it will behave in the way C behaves.

### Specialization

Specialization relationships are very similar to realization relationships. You can think of these specialization relationships as realizations but between two real/concrete classes. A specialized class specializes some general class. By establishing this relationship, you are able to extend the form and behavior of a specific class.

> Specialization has plenty of names. This relationship is also called **extension** between the special/child/sub class and general/parent/super class. Another name for it would be **inheritance**.

![Specialization Example](copyright free drawings/SpecializationRelationship.png)

Here's an example that would illustrate what the specialization relationship means. Consider a factory that builds robots. This factory is able to build `NormalBot`s which are the general type of robots. Instead of creating entirely separate mechanisms to build each type of robot, the factory is able to exploit the fact that other robots are just specializations of `NormalBot`. `Skybot` is just the same as `NormalBot` but it has extra flight capabilities. `ShadeBot` is just the same as `NormalBot` but it has UV Protection. Because of this the factory is able to use the building recipes for `NormalBot` to build `Skybot` and `ShadeBot`. All they need to do is to add some extra layers of construction such as adding wings or outfitting shades.

Specialization relationships work like this as well. When you write code for the general class, you do not need to rewrite it for specializations. What you write inside specializations are the the attributes and method that make it special. If this specialization has flight capabilities then add attributes for wings and methods for flight. If this specialization behave in a different manner for some specific method then you only change that specific method.

Because of this relationship, you only need to write one copy of the code that is common for the general class and its specializations. This means that you do not need to rewrite said code, saving time and effort but more importantly, having one copy of code helps for maintainability. When the recipe of all robot types need to change, the factory only needs to change the recipe of `NormalBot`, all of the special robots' recipes will change as well since they all use `NormalBot`'s recipe. When the code for the general class and the special classes need to be updated, changing the shared code found inside the general class will automatically affect special classes. Through this mechanism, specialization relationships enable **inheritance**, one of OOP's core design principle.

> This is where the terms **inheritance** and extension make sense. Special class inherit all of the attributes and methods of the general class. Special classes can also be thought of as extensions of the general class, since these special classes extend or tweak the capabilities of the general class. 

You can also specialize, specializations. This is illustrated by `ShadowBot`, which is a special `ShadeBot` that has knife. Since `ShadowBot` is a special `ShadeBot` and `ShadeBot` is a special `NormalBot`, `ShadowBot` is automatically a specialization of `NormalBot` as well. 

Specializations also allow polymorphism in the same way realizations do. A `ShadowBot` can be interacted with like any `NormalBot` or `ShadeBot` but since it is also a `ShadowBot` it will behave specifically like a `ShadowBot`.

### Abstract Classes

An abstract class is something in between an abstraction and a generalization. It contains attributes and methods with bodies but it also contains abstract methods as well. When classes specialize/realize abstract classes, they inherit the attributes and methods with bodies but they are forced to implement the abstract methods as well. These relationships are sometimes used if the system requires a mix of inheritance and implementation between classes.

### Multiple Type Relationships

It is possible for a class to realize/specialize multiple abstractions/generalizations. For example, given abstractions, A,B and C, and generalizations E,F, and G, a class X can realize/specialize all of them. When you do this, X will be forced to inherit all of the abstract methods in A, B and C, and automatically inherit everything from E, F, and G.

## Dependency Relationships

Dependency relationships, also known as **associations**, characterize how two classes interact with each other. A class which is dependent on another class, needs to know how to interact with it. These interactions range from being used as method parameters, being returned in methods, being used inside method bodies, being used as attributes and etc. A dependency relationship is one way (but it is also possible for two objects to be dependent on each other). A **client** class is dependent on some **dependency**. There are two types of dependencies:

![Dependency Example](copyright free drawings\DependencyRelationship.png)

### Aggregation

Aggregation relationships are general usage and transactional dependencies. When a dependency is an aggregate of some client, it means that the client merely **uses** the instances of this dependency. These relationships are the looser forms of dependency, because the dependency instance can exist outside the lifetime of the client instance.

For example, in a videogame, a hostile enemy instance of `DragonPriest` spawns equipped with instances of`DragonPriestMask` and `DragonPriestStaff`. The `DragonPriest` instance is a client of the dependencies `DragonPriestMask` and `DragonPriestStaff`. `DragonPriest` interacts with these dependencies to calculate its attack damage, its defenses and etc. But when this specific instance of `DragonPriest` is defeated, it despawns, leaving behind the its `DragonPriestMask` and `DragonPriestStaff`. These instances will continue to exist since it can still be used by other client objects in the game, such as the player character, some storage chest or whatever. This means that the relationship between `DragonPriest` and the dependencies `DragonPriestMask` and `DragonPriestStaff` is aggregation.

### Composition

Composition relationships are ownership dependencies. When a client is composed of some dependency, this means that the client **owns** the instances of this dependency. These relationships are stronger forms of dependency since the existence of the dependency instance is tied to the client, meaning, the dependency ceases to exist outside the lifetime of the client instance.

In the same videogame, the hostile enemy instance of `DragonPriest` appears in game using some `DragonPriestCharacterModel` instance. When the `DragonPriest` instance is defeated, it despawns. It makes no sense for the `DragonPriestCharacterModel` instance to stay behind after the `DragonPriest` instance is defeated, therefore it ceases to exist as well. This means that the relationship between `DragonPriest` and the dependency `DragonPriestCharacterModel` is composition.