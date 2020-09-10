# Design Patterns Introduction

## Introduction

Design patterns, are general, reusable solutions to a commonly occurring problem within a given context in software design. Unlike algorithms, design patterns are not clear instructions that can automatically be transferred to your system. Design patterns are more like templates that describe the general concept to solve the problem. It doesn’t contain implementation details; it contains structural blueprints.

## Learning Outcomes

1. Discuss the origins of design patterns in OOP
2. Explain the advantages of design patterns
3. Explain the disadvantages of design patterns
4. Identify the three classifications of design patterns

---

## History of Design Patterns

Design patterns are not novel and sophisticated discoveries, they are instead, typical solutions to common problems. The pattern of these solutions become so ubiquitous that it becomes worthwhile to put a name to it. Design patterns in software engineering are just borrowed concepts from architecture/design.

The concept of design patterns is often attributed to Christopher Alexander, from his book, *A Pattern Language: Towns, Buildings, Construction* [^1]. These patterns may describe how high windows should be, how many levels a building should have, how large green areas in a neighborhood are supposed to be, and so on.

Four software engineers, Erich Gamma, John Vlissides, Ralph Johnson, and Richard Helm, used this as an inspiration to publish the famous book, *Design Patterns: Elements of Reusable Object-Oriented Software.* [^2] The four became collectively known as the “**Gang of Four”**. And their book became known as the GoF book. It contains a catalog of 23 design patterns solving various problems of OOP design.

## Why Patterns?

The answer to this problem is similar to the reason as to why you don’t “reinvent the wheel”. Design patterns are tried and tested solutions, knowing these patterns give programmers a toolset to solve a variety of problems in software design.

Design patterns also help with communication.  A team of software engineers well versed in design patterns wouldn’t need to explain to each other what exactly must be done to use an “Adapter pattern”.

## Why not Patterns?

Design patterns are sometimes used to simulate features that the programming language doesn’t have. If you use a powerful enough language you wouldn’t need the pattern at all. Example of this is how the Strategy pattern can be replaced by lambdas.

Patterns are not end-all be-all solutions to any design problem out there. At the end of the day context matters the most. An inexperienced programmer will implement a problem to the dot, instead of adapting the pattern for the context. Patterns are not end-all be-all solutions to any design problem out there. At the end of the day context matters the most. 

Sometimes, you don’t even need a pattern at all. A simple problem solved using a complicated solution is inelegant.

## Classifications of Design Patterns

- **Creational Patterns** provide object creation mechanisms that increase flexibility and reuse of existing code
- **Structural patterns** explain how to assemble objects and classes into larger structures, while keeping the structures flexible and efficient.
- **Behavioral patterns** take care of effective communication and the assignment of responsibilities between objects.

[^1]: Alexander (1977). *A Pattern Language: Towns, Buildings, Construction*. 
[^2]: Gamma, Vlissides, Johnson, and Helm (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*

## Optional Reading

Gamma, Vlissides, Johnson, and Helm (1994). Design Patterns: Elements of Reusable Object-Oriented Software