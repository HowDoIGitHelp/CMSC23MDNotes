# Programming Paradigms Introduction

## Learning Outcomes

At the end of this discussion you should be able to:

1. Explain what a programming paradigm is.
2. Identify the four main programming paradigms
3. Explain why programming languages are shifting to multi-paradigmness

## The title of this course

Let's start by talking about the title of the course name because it does sound like some buzz phrase.
You will start saying this phrase soon so let's get the definition out of the way.

"Programming paradigms".

You're probably familiar what half of this phrase means, I mean, I hope you are, otherwise I don't know what to do.


## Paradigm

Let's focus first on the non-obvious part, the word paradigm.
This word comes up often in academia.
You probably heard of the term **paradigm shift** somewhere, it describes some form of fundamental change in the way we think within scientific disciplines often characterizing a scientific revolution.
One notable example of a paradigm is the shift from Ptolemaic or Geocentric cosmology to Copernican or Heliocentric cosmology.

![Ptolemaic Geocentric model of the solar system](../../resources/Ptolemaic_Model.png)

Based on this context you can kind of formulate what the word paradigm means.
This one connotes a similar meaning in the context of programming:

A paradigm is "*a framework containing the basic assumptions, ways of thinking, and methodology that are commonly accepted by members of a scientific community.*" [@noauthor_paradigm_nodate].
It is a set of ideas and concepts that describe some **way of thinking**.

If we go back to the geocentric vs heliocentric paradigms in astronomy, you can't really definitively say that the heliocentric model is the only correct model of the solar system, you can still reconcile the geocentric model's perspective of putting the earth in the center by modelling heavenly bodies' movement with epicycles.
After all, whether the earth or the sun is the center is a matter of perspective.
It just so happens that placing the sun in the center provided science with a more natural way of describing planetary movement.
The heliocentric paradigm ended up uprooting geocentric paradigm as the dominant worldview, providing science with ideas that we still accept as truth until now, the earth is not the center.
The earth is just one of the 9 planets, is not that special, gravity and inertia works in way which causes planets to move.

A paradigm shift like this is actually happening in programming language design, well talk about that some time later.

## Programming

Now that you understand what paradigm means, let's talk about the first word, programming.
As second year CS students, what is your definition of **programming**?
It's a strange question to ask in a second year course, but I want you all to think about what the definition of programming is.
The way you answer this question may actually tell you which perspective or programming **PARADIGM** you follow.
When you say you are programming some kind of mechanism or behavior *what are you actually doing*?
How do you define what a program is, and what is its relationship to a computer?

Here's one definition from the internet: 

> Computer programming is the process that professionals use to write code that instructs how a computer, application or software program performs.
> At its most basic, computer programming is a set of instructions to facilitate specific actions.[@cote_what_2025]

That is correct.
Let me simplify that definition to this.


Programming is when you tell a computer what to do.
When you write programs you're writing **instructions** for your computer.

1. Ask the user for a number
2. Store that number to a variable called x.
3. While x is greater than 7 do step 4 otherwise proceed
4. Subtract 7 from x and store the difference to x
5. Show the user the value of x

That is a good definition of programming.
It gives you an understanding on how you write programs that work.
All you need to do is to write correct instructions that the computer understands, and you'll have a perfectly working program.
A **programming language** is a medium that describes how to write instructions to communicate to your computer.
If you learn to do that then you can go ahead and program away.

It is a correct definition, but is it the *only* correct definition?
It defines programming under the paradigm **imperative programming**.
I will not begrudge you if this is the only definition you know since there is a huge likelihood that the only paradigm you've been exposed to has been imperative programming.


## Taxonomy of Programming Paradigms

For someone who has been exposed to C, C++ and nothing else, you might feel that the natural way to code is the *imperative way* when in fact there are alternatives.

The diagram [@movgp0_overview_2013] here represents the alternative schools of thoughts describing how to program.
This diagram taxonomizes programming languages by identifying which paradigms they are under.
Most of these paradigms are either not pragmatic, not popular enough or not unique enough to be studied in this course.
Instead, we will be focusing on four major programming paradigms:

![Programming Paradigms](../mermaid_diagrams/programming_paradigms.png)

Under the **imperative paradigm** family, we have procedural programming and objective oriented programming.
Languages under the imperative paradigm create programs that go through different *states*.
The *initial state* of an imperative program is the problem statement.
From here the program follows ordered steps, with respect to the current state.
Each step in the program can also constitute a *state change*.
The solution is found at the *final state* of the program.

Under the **declarative family**, we have functional programming and logic programming.
Declarative programs are not broken into ordered steps.
Instead, declarative programs solve a problem by *defining a problem set* according to the language.
For example, problem can be defined as a *composition of functions*, or a *logical relation*.
The program then searches for valid solutions with respect to the definitions declared in the program.

This course will give you an overview on these programming paradigms.
Each of these are built upon the foundation of some mathematical formalism.
We will explore the advantages and disadvantages of each paradigm while we take a tour through these four.
Studying the disciplines upheld by these paradigms will also teach us good programming practices for designing elegant programs that transcend any programming paradigm.

## Multi-paradigm programming languages

The way it used to be was that a programming language would be written with features adhering to the concepts of a particular paradigm.
Sometimes, a language is written with fresh features that follow a different mathematical formalism that births its own programming paradigm.
Back then paradigms worked like programming language **classifications**.
The programming language C for example is a strong follower of procedural programming.
Therefore, you can think of C as classified under procedural programming.

But as time passed by classifying a newer programming language under one paradigm became harder and harder.
A programming language like python for example is mostly procedural, object-oriented, but has functional programming features.

Modern programming languages evolved to become **multi-paradigm**.
This inevitably happened because, as programming languages evolve, more **features** are added to it.
These features are sometimes *borrowed from other paradigms* to solve a problem in a better way.
This is the reason why established and mainstream programming languages like Java, C++, or Python tend to be multi-paradigm.

The multi-paradigmness of programming languages tend to be the reason why some programming language designers have abandoned the notion of building based on a strict paradigm.
Instead, a language designer would *choose specific features* that they want to be supported on their programming language and implement it, regardless of its paradigm origins.

Instead of thinking of paradigms as classifications, you should think of paradigms as *different ways to solve problems*.
You can solve one problem by applying the concepts and philosophies of imperative paradigm, and solve another problem by applying the concepts and philosophies of functional paradigm.
You can even come up with a solution that combines the concepts and philosophies of different paradigms.
