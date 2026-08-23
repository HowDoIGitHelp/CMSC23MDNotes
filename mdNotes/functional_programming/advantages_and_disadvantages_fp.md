# Advantages and Disadvantages of Functional Programming

## Advantages

Functional programming's main advantage lies in its stateless paradigm.
Without state, you have the following advantages that all lead to safety and readability.

- Functional purity
- Lack of side effects
- Referential Transparency

Without state, you can ensure that your programs are easy to write without worrying about side effects.
You can build complex programs by writing small pure functions and compose them with each other to without worrying about unexpected results.

Also, functional programming revolutionized how functions are *used* and *treated*.
Higher-order functions allow you to write new definitions from existing functions.
Combined with partial application, even simple functions can be surprisingly powerful.
As a result you end up with a very *expressive* language.

## Disadvantages

Functional programming has its own set of *disadvantages* as well, most of them related to this seemingly artificial crutch of statelessness and immutability.
The most obvious one is that creating new values instead of changing an existing variable has extra overhead in both processing and memory, making functional programming *slower and less efficient*.
Programming without state can be difficult to do for certain mechanisms (*Like the external logger for example*).
Simulating mechanisms like this may introduce conflict on how you compose your functions.
It's not impossible though, you just have to learn some category theory concepts such as **monads**.
Also, for most people who are used imperative programming, *recursion*, does not feel natural compared to *iteration*.
But in my opinion, after being exposed to functional programming for some time, recursion can make more sense than iteration.

Nevertheless, these disadvantages are not insurmountable.
In fact, there are a plenty of systems written in functional programming languages running without issues.
Functional programming has had the reputation of being more conceptual and fancy than the classic imperative programming language.
People mocked Haskell for its pristine white tower "*elitist*" approach to programming.
But recently these functional programming languages like Haskell, F# and Scala has enjoyed improvements that have elevated them to be as pragmatic as your classic C, C++ or Java.
In fact, functional programming has gained a considerable rise in popularity to an extent that mainstream programming languages with imperative roots like C#, Python, and JavaScript have started to introduce features *derived* from functional programming languages.
These languages and more either have libraries or built-in **higher-order functions** and **lambdas**.
At this point learning functional programming concepts has become a *necessity* for any programmer, regardless of their paradigm preferences.

## Some projects and systems written in the functional paradigm

Here are some codebases where you can find functional programming shine:

- [Pandoc](https://pandoc.org/) - a widely used document converter that is offers universal document conversions from markdown, docx, latex, html, and more. It is written in haskell.
- [Emacs](https://www.gnu.org/savannah-checkouts/gnu/emacs/emacs.html) - a very extensible text editor that natively interprets lisp. Its plugins are also written in lisp.
- [React](https://react.dev/) - a Javascript library for mainly for web UI. Modern react moved from OOP to functional programming.

Functional programming features can also be found seeping through data science (in the form of spreadsheets and data frames), scientific computing (Mathematica, Julia, etc.) and more.
