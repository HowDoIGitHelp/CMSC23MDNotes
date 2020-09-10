# Imperative Programming

## Introduction

Imperative programming has turned out to be the natural paradigm of programming languages. The members of the imperative programming family has been dominating the market share of programming languages throughout the years with titans like BASIC, Pascal, C, Java and many more. 

## Learning Outcomes

At the end of this discussion you should be able to

1. Explain how imperative programming became the natural paradigm
2. Explain the concept of state in the context of imperative programming
3. Explain how the assignment statement enables the progression of states
4. Create structure programs to represent algorithms
5. Differentiate the subparadigms procedural programming and object-oriented programming

---

> **Quick Note on Imperative Programming and Procedural Programming**
>
> People usually use the terms Imperative programming and procedural programming interchangeably. Procedural programming is a subparadigm of imperative programming family but some people refer to procedural programming as imperative programming. That's because other imperative paradigms like object-oriented programming is derived from procedural programming. You can think procedural programming as the ancestor if other imperative paradigms. 
>
> In this lecture I refer to Imperative paradigm as a whole but I will focus on the main ideas that are common between other imperative paradigms. Ideas from object-oriented programming paradigm can be found on a separate lecture.

Imperative programming has turned out to be the natural paradigm of programming languages. The members of the imperative programming family has been dominating the market share of programming languages throughout the years with titans like BASIC, Pascal, C, Java and many more. 

If you think about it, this is not really surprising. This paradigms dominance could be attributed to most computer scientists’ preference towards pragmatic and efficient programming languages. Especially since the most straightforward way of communicating to computer hardware is through the explicit manipulation of CPU memory and registers. 

If you want the computer to do something for you, then you communicate to the computer that you want this and that to be done. And if you manage to give the computer correct and comprehensive instructions then you'll end up getting what you want.

If we rewind back to the dawn of programming languages you'll see that early programming languages were built to communicate to computer hardware. As a result of this, programming languages naturally adopted syntax with imperative moods.

Assembly programs for example was mostly built from sequences of executable instructions which was patterned from imperative statements from natural language.

```assembly
INC ITER
MOV AH,7
ADD AH, AL
```

For example the assembly instruction, INC ITER, tells the computer to increment the memory variable called ITER. The instruction MOV AH, 7, tells the computer to move the value 7 to the AH register. The instruction ADD AH, AL tells the computer to add the contents of the AH register to the AL register.

As time went by, newer higher level programming languages emerged (higher level meaning farther from hardware and closer to human language) like Basic, Pascal, and C. The syntax of these programming languages were written as abstractions of hardware code. Although programs written in these languages became more human readable compared to its predecessors, these newer languages retained their imperative tones and mechanisms. This progression meant that higher level programming languages built atop of imperative languages naturally adopted the imperative paradigm as well. Java, Python, and C++ for example which were all written in C, followed this progression, thus establishing the imperative family as the dominant paradigm in programming language design.

### The STATE

The existence of an explicit state is the foundation of imperative programming. The **state** of  a program or a process on a given instance is the snapshot of its immediate relevant environment and context. The state of your CPU on a given instance for example will refer to the values found in the registers and relevant memory. On a specific process the state will refer to the values inside the memory addresses it resides in. 

On a computer program the state can refer the conceptual set of variable values related to the program's runtime on some given instance. Lets use this program as an example:

```c
int x = 3
int y = 4
x = x + y
```

At the start of runtime, the state of this program would be (*for all intents and purposes*) empty, since there are no relevant variables declared at this point. After executing the first line of code, the state of the program would look something like this:

| variable | value |
| :------: | :---: |
|   `x`    |   3   |

One integer variable named `x` with the value 3. After the next line of code a new variable is is introduced and immediately assigned with the value 4 so the state of the program at this instance will look like this:

| variable | value |
| :------: | :---: |
|   `x`    |   3   |
|   `y`    |   4   |

And at the last line, the value of `x`  is updated by adding the value of `y` so the final state of this program will look like this:

| variable | value |
| :------: | :---: |
|   `x`    |   7   |
|   `y`    |   4   |

##  Assignment Statement

Another important construct of the imperative programming paradigm is the assignment statement. Assignment statements and the concept of state are very related to each other.

Assignment statements allow your program to MUTATE the values of your variables. Mutation in the context of programming is a fancy term that basically means change. And as we learned earlier, changes to the context of a program, which includes variables, creates states.

Therefore every assignment statement, corresponds to new states of a for the program.

Assignment statements are usually executed through the use of the “`=`” operator (some languages like Pascal use “`:=`” instead). Although it borrows the equality operator from math, assignment operators behave very differently from an equality statement. Instead of communicating some kind proposition, the assignment statement has an **imperative mood**. An equality a=b in math **declares** that some a is b, while an assignment operator `a=b` **commands** that `a`'s value is now the same as `b`. Mutation is introduced once you perform an assignment to `a` again, signifying a **change** in the value of `a`. 

By the way, the closest corresponding mathematical construct to an assignment statement is the let statement. A statement in math such as "let x be equal to 3", has an imperative mood. But unlike an assignment statement which can change the value of a variable any number of times, a let statement can only set the value of a variable once.

For every assignment statement you feed to the computer, something meaningful happens. That particular "something" that happens is characterized by changes to your programs context. The context of a program changes for every individual mutation of a variable. And you can compare the difference between the before and after of a specific assignment by comparing the before-assignment state and the after-assignment state. The progression from one state to another characterizes the effect of an assignment.

This is the important take away that you need to remember. Imperative programming is characterized by imperative statements. Statements that tell the computer what to do. The most important type of these statements is the assignment statement. An assignment statements effect to your computer is characterized by the progression from one state to another. Assignment statements make states, and if you combine many of these assignment statements arranged in a particular manner, you can create a meaningful program that does something for you.

## Structured Program Theorem

Creating meaningful programs in imperative programming is done by applying the Bohm Jacopini Theorem. This theorem was one of the theoretical frameworks proposed to characterize imperative programming.

 The theorem describes a formalism of a class called control flow graphs which are capable of representing any computable function. These control flow graphs are actually something you are intimately familar of. It is known to you as the trusty old flow chart. Any control flow graph can be created by combining subprograms in three specific ways. A subprogram is a recursive unit of control flow graphs. A subprogram can be a single statement or it can be a combination of more than one subprogram. Here are the three ways to combine subprograms:

1. Executing one subprogram, and then another subprogram (sequence)
2. Executing one of two subprograms according to the value of a boolean (selection)
3. Repeatedly executing a subprogram as long as a boolean expression is true (iteration)

Structured programming enjoyed a universal popularity in computer science. The constructs described by this formalism became the natural architecture for programming language designers. This is the reason why CS students like you are introduced to programming using control flow graphs or flow charts. This is also the reason why programming languages like Pascal, C, Java and their derivatives are designed the way they are.

---

## Subparadigms under the Imperative family

### Procedural programming

Programming languages like Fortran, ALGOL, BASIC, and C fall under the procedural  paradigm. Languages under this paradigm simplify a complex system by  subdividing a program into different **procedures** or functions.

### Object oriented programming

Object oriented programming focuses on modelling a system based on the real  world ontology of objects. It uses an expressive type system to program  the interactions within a system.

## Optional Readings

Rapaport W. (2004) [Great Idea III: The Boehm-Jacopini Theorem and Structured Programming](https://cse.buffalo.edu/~rapaport/111F04/greatidea3.html). [CSE 111, Fall 2004](http://www.cse.buffalo.edu/~rapaport/111F04.html) Accessed August 31, 2020