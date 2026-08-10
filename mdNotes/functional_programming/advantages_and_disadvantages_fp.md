# Advantages and Disadvantages of Functional Programming

## Advantages

Functional programming's main advantage lies in its stateless paradigm.
Without state, you can ensure that your programs are easy to write without worrying about side effects.
You can build up complex programs by writing small pure functions and compose them with each to model any algorithm.

Higher-order functions allow functions be very flexible.
Combined with partial application, even simple functions can be surprisingly powerful.

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
People mocked Haskell for its pristine white tower "elitist" approach to programming.
But recently these functional programming languages like Haskell, F# and Scala has enjoyed improvements that have elevated them to be as pragmatic as your classic C, C++ or Java.
In fact, functional programming has gained a considerable rise in popularity to an extent that mainstream programming languages with imperative roots like C#, Python, and JavaScript have started to introduce *features patterned from pure functional programming languages*.
Features such as **higher-order functions** and **lambdas**.
With the risk of sounding editorial I even argue that learning functional programming concepts has become a *necessity* for any programmer, regardless of his/her paradigm preferences.
