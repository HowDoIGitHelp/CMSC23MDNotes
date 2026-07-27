# Lab Exercise 12 (Fraction Calculator)

## Task

You're creating a less sophisticated version of a fraction calculator. This calculator only has arithmetic operations inside it, addition, subtraction, division, and multiplication. Inside this calculator, a calculation is represented in a `Calculation` instance. Every calculation has four parts:

- `left` - represents the left operand fraction
- `right` - represents the right operand fraction
- `operation` - represents the operation ($+$,$-$,$\times$,$\div$)
- `answer` - represents the solution of the operation

Kotlin does indeed support higher order functions but your boss is anti-functional programming so they forbid the use these features. Because of this you decide to implement the strategy pattern.

To do this, you need to create an abstraction called `Operation` to represent the different operations. For each operation, you create a class that realizes `Operation`. 

![strategy pattern example](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/umlOutputs/FractionCalculator.svg)

**Complete the system using the strategy pattern.**

## Assessment Criteria

- Correct `Operation` realization functionality - 24
- Pattern is open for extension = 16