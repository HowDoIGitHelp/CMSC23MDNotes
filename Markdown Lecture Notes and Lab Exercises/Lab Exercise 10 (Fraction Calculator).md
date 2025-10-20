# Lab Exercise 12 (Fraction Calculator)

## Task

You're creating a less sophisticated version of a fraction calculator. This calculator only has arithmetic operations inside it, addition, subtraction, division, and multiplication. Inside this calculator, a calculation is represented in a `Calculation` instance. Every calculation has four parts:

- `left` - represents the left operand fraction
- `right` - represents the right operand fraction
- `operation` - represents the operation ($+$,$-$,$\times$,$\div$)
- `answer` - represents the solution of the operation

Kotlin does indeed support higher order functions but your boss is anti-functional programming so they forbid the use these features. Because of this you decide to implement the strategy pattern.

To do this, you need to create an abstraction called `Operation` to represent the different operations. For each operation, you create a class that realizes `Operation`. 

![strategy pattern example](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/0f910e29bb0cc49a8510fdc72a6994fe58c5cac0/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/umlOutputs/FractionCalculator.svg)

> `execute()` should have been named like a builder method (something like `solution()`), I'm keeping the name `execute()` since this is how Strategy patterns usually names this particular method.

**Complete the system using the strategy pattern.**

## Assessment Criteria

- Correct `Operation` realization functionality - 24
- Pattern is open for extension = 16