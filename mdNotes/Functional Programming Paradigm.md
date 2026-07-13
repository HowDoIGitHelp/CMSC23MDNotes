# Functional Programming Paradigm

## Introduction

During the 1930’s a mathematician investigating the foundation of mathematics, named Alonzo Church, introduced a formal system of expressing computational logic. The system he created was called **Lambda Calculus**. It was until the 1960’s when the system found its way through different disciplines. It became something more than a a mathematical formalism and became an important concept in linguistics and **computer science**[^1].

## Learning Outcomes

After this discussion you will be able to:

1. Reduce lambda calculus expressions (Optional)
2. Create higher order functions
3. Identify pure functions
4. Explain how the advantages of statelessness in functional programming paradigm
5. Explain the disadvantages of statelessness functional programming paradigm

---

## Lambda Calculus

Before we dive into functional programming let's introduce ourselves to the formalism that inspired it, Lambda Calculus. These concepts may seem strange at first since it imagines a mathematical foundation beyond numbers, sets, and logic. You can skip this video and it won't really affect your understanding of functional programming concepts. But I think understanding the concepts of this formalism will give us a better appreciation for functional programming.

### Expressions in the Lambda Calculus Formalism

Let $\Lambda$ be the set of expressions under the Lambda calculus formalism

1. **Variables**. If x is a variable, then $x \in \Lambda$ 
2. **Abstractions**. If $x$ is a variable and $\mathscr{M} \in \Lambda$, then $(\lambda x. \mathscr{M}) \in \Lambda$.
3. **Applications**. If $\mathscr{M} \in \Lambda \land \mathscr{N} \in \Lambda$, then $(\mathscr{M} \mathscr{N}) \in \Lambda$.

Take a look at these important precedence conventions. You might get confused if you read some lambda calculus expressions. Some people often omit parentheses or single-parametrizations to write shorter expressions:

1. Application is left associative
   $$
   \mathscr{M_1}\mathscr{M_2}\mathscr{M_3} \equiv ((\mathscr{M_1}\mathscr{M_2})\mathscr{M_3})
   $$

2. Consecutive abstractions can be uncurried
   $$
   \lambda xyz.\mathscr{M}\equiv\lambda x.\lambda y.\lambda z.\mathscr{M}
   $$

3. The body of an abstraction extends to the right
   $$
   \lambda x.\mathscr{M}\mathscr{N}\equiv\lambda x.(\mathscr{M}\mathscr{N})
   $$

### Reductions

Reductions are a ways to simplify and evaluate lambda expressions. You'll learn later that these reductions are basically concepts that are eventually adapted to functional programming concepts.

#### $\alpha$ equivalence:

$\alpha$ equivalence states that any bound variable, has no inherent meaning and can be replaced by another variable:
$$
\lambda x.x =_\alpha \lambda y.y
$$

Given a lambda calculus abstraction $\lambda x. \mathscr{M}$, this abstraction's bound variable is $x$. The bound variable $x$ may appear somewhere in $\mathscr{M}$, the body of the abstraction. An alpha equivalence basically shows that the name of the variable has no inherent meaning. Therefore,  you can replace it with any other variable name.

####  $\beta$ Reductions

$\beta$ reductions state how to simplify abstractions. This process is similar to applying a function in the context of programming. For example we use the identity function ($\lambda x.x$) and apply it to some free variable $y$.
$$
(\lambda x.x)y\to_\beta y
$$

When you beta reduce some application $\mathscr{M}\mathscr{N}$, what you're doing is replacing all instances of the bound variable in $\mathscr{M}$ with $\mathscr{N}$. When, Here's a another example, 
$$
(\lambda u. \lambda v. uvu)\lambda x.x \to_{\beta} 
$$


#### $\eta$ reductions

$\eta$ reductions describe equivalencies that arise because of free variables. If $x$ is a variable and does not appear free in $\mathscr{M}$ then:
$$
\lambda x.(\mathscr{M}x) \to_\eta \mathscr{M}
$$

The lambda expression here is just some redundant abstraction. These $\eta$ reductions characterize higher level simplifications that are not always as obvious as the other reductions.

#### Reduction example

For example to reduce the following lambda expression, we must first understand what it means.
$$
(\lambda x.\lambda y.(xy))(\lambda x.\lambda y.(xy))
$$
In the outermost level, the expression is the application of $\lambda x.\lambda y.(xy)$ to itself. It follows the second type of lambda calculus expression discussed earlier, $\mathscr{M}\mathscr{N}$ where $\mathscr{M}\in \Lambda$ and $\mathscr{N}\in \Lambda$.  In this context $\mathscr{M} = (\lambda x.\lambda y.(xy))$ and also $\mathscr{N} = (\lambda x.\lambda y.(xy))$. 

When you start evaluating this expression, you might be tempted to automatically apply a $\beta$ reduction by itself:
$$
\begin{aligned}
(\lambda x.\lambda y.(xy))(\lambda x.\lambda y.(xy))&\to_{\beta}\lambda y.((\lambda x.\lambda y.(xy))y)\\
&\to_{\beta}\lambda y.\lambda y.(yy)
\end{aligned}
$$
But this reduction is actually incorrect because the although $x$ and $y$ appear on both lambda expressions, these variables don't have the same meaning . The $x$ and $y$ variables inside the left lambda expression are **bound** inside this lambda expression. The $x$ and $y$ variables outside the left lambda expression (inside the right lambda expression) are **free** in its context, therefore, even though they look the same, it is incorrect to interchange the two variables.

Two avoid confusion with similarly named variables, it is advisable to apply an $\alpha$ equivalency, to give them different names. This can be done by replacing the right abstractions bound variables with $u$ and $v$. Again, this alpha reduction doesn't change the meaning of the abstraction, it merely renames the bound variables.
$$
(\lambda x.\lambda y.(xy))(\lambda x.\lambda y.(xy))\equiv_\alpha(\lambda x.\lambda y.(xy))(\lambda u.\lambda v.(uv))
$$
The correct reduction now is as follows. Still a $\beta$ reduction but without the ambiguity of similar variable names.
$$
\begin{aligned}
(\lambda x.\lambda y.(xy))(\lambda u.\lambda v.(uv))&\to_\beta \lambda y.((\lambda u.\lambda v.(uv))y)\\
&\to_\beta \lambda y.\lambda v.(yv)
\end{aligned}
$$

## The Paradigm

### Reimagined functions

Lambda calculus evolved from a system of logic foundation with deep roots to computation theory into something that became a basis for programming language design. Language designers started to consider the unconventional representation of lambda calculus expression as a valid and pragmatic way of representing data. Around 1950's programming languages patterned around the framework of lambda calculus started to emerge. One of the earliest and most important of these languages was **Lisp** which evolved to become a large family of programming languages[^2]. Soon, more programming languages started to implement the same formalisms described by lambda calculus. This opened a new paradigm of programming languages called **functional programming paradigm**. To explore this paradigm this section will introduce the programming language **Haskell**. This language has become one of the most important functional programming language, setting the standards for other languages paradigm.

#### How functions are treated Differently

One of the biggest difference between your classic imperative programming languages like C and Java and a functional programming language, is how it treat its function. You might have probably guessed that since the paradigm has "function" in its name. To explore this contrast lets start by introducing a simple function, written in C. This function basically accepts an integer and returns a the same integer but squared:

```c
int square(int x){
	return x*x;
}
```

Functions like these are patterned from mathematical functions. It has a **name** to invoke it later called `square`, it has **specifications** on which type of data it accepts (`int x`) and produces (`int`), and finally it has a instructions on what must be done when it is invoked (`return x*x`.) Functional programming functions behave in more or less the same way.

```haskell
square :: Int -> Int
square x = x * x
```

It looks different but all of the parts you can find in a C function can also be found on this Haskell function.

In terms of invocation, they are also used similarly and of course the behave similarly:

```c
square(5);
```

```haskell
square 5
```

Although functions in non-functional programming behave and look similar to functions in functional programming language, they have a huge difference in the way the programming language treats it. 

A function in C is treated differently from other types of data. In fact C programmers will rarely call a function a value. What this means is that canonical value types like integers, characters, and arrays (even compound value like structs and objects) can be passed on functions and can be returned as functions. 

```c
int* add_to_array(int arr*,int x,int size){
 for(int i=0;i<size;i++){
  arr[i]+x;
 }
 return arr;
}
```

C discriminates function from these canonical value types. Therefore, during runtime, non-functional programming languages interpret the expression `square(5)` as "the number $5$ squared" while the expression `square` is just some disembodied function name (*square of which number?*). Imperative programming functions during runtime are meaningless unless they are directly invoked.

#### Higher Order Functions

##### Passing functions

Functional programming languages treat functions the same way it treats values, you can pass them in other functions and you can return them as well. 

```Haskell
s:: Int -> Int
func x = x + 1

p::Int -> Int
func x = x - 1

applytwice:: (Int-> Int) -> Int -> Int
applytwice f x = f (f x)
```

> Note these arrow looking characters are actually just dash followed by a greater than character "(->)". My markdown editor formats it to look like a neat arrow inside code fences.

The code above shows two function definitions (with some type signature annotations for readability). The first is the function `s::Int -> Int` which is applied to an integer and produces an integer. What it does is it simply adds one to `x`. The second function is similar but what it does is subtracting one from `x`.

> Type signature annotations are not required here, that's why they're called annotations. Adding these annotations will restrict the type the functions can be applied to. Type signature annotation syntax are understood like this
>
> ```haskell
> f :: Paramtype -> AnotherParamtype -> ... -> OuputType
> ```
>
> The last type in the `->` series is the type the function produces (like it's return type) and everything else before it are parameter types.

The third function is what we call a higher order function. A higher order function is a function that either accepts a function as a parameter or returns a function parameter or both. The function `applytwice` as described by it's type signature, is applied to a function `f` and an integer `x`and produces an integer. What it does is it applies the function `f` twice to `x`, something like ($f(f(x))$).

By defining a function like this we can do something like this during runtime:

```haskell
ghci> applytwice s 3
5
ghci> applytwice p 3
1
```

To a programmer with no experience with functional programming, this feature can be surprising especially since mathematical functions in algebra or calculus don't even explore this capability. But if you remember this is not just some arbitrary added feature added for novelty. This feature isdirectly patterned from lambda calculus:
$$
\begin{aligned}
\text{let } S &= \lambda n .\lambda s. \lambda z. (s(nsz))\\
T &= \lambda f. \lambda x. (f(fx))\\
\overline 5&=TS\overline{3}
\end{aligned}
$$
In lambda calculus an abstraction and an application does not restrict anyone from the type of expressions bound to variables. In the spirit of implementing lambda calculus, any functional programming language will allow you to do this as well.

##### Returning functions

On the other side of the coin, a function, in functional programming will also let you return functions the same way you return any other kind of data.

To explore this, suppose we have different functions that when applied to an integer, produces that integer plus a certain integer.

```haskell
addTwo::Int -> Int
addTwo x = x + 2

addThree::Int -> Int
addThree x = x + 3

addFour:: Int -> Int
addFour x = x + 4
```

We can generalize these functions into a *function-maker* function, that when applied to an arbitrary integer `x`, will produce an `addx` which is a function (not a number) that adds `x` to your integer.

 ```haskell
addMaker::Int -> (Int->Int)
addMaker x = (\y -> x + y)
 ```

We are introducing new syntax here, but this new syntax is a representation of an expression we already know from lambda calculus. The definition for your `addMaker` (`\y -> x + y`) is basically an implementation of the lambda expression below. `y` is the bound variable, and the operator `->` separates the inputs and the output, `x + y`.
$$
\lambda y. \text{add }x y
$$

>  In fact, the reason why Haskell syntax uses the `\` character to represent lambda expressions is because this is the your keyboard's best physical approximation of the Greek letter $\lambda$. Extra note: "$+$" does not exist in the universe of lambda calculus so instead what's used here is a reference to a lambda calculus abstraction called "$\text{add}$". The definition of this can be found in the supplemental topic [Lambda Calculus Encodings](www.something.com)

What this expression means then is that `addMaker` produces a lambda expression, which essentially behaves exactly like a function. This allows you to create functions during runtime

```Haskell
ghci> addSix = addMaker 6
```

Simply writing the expression `addSix` on your terminal will yield you an error, because printing `addSix` doesn't really have a meaning outside the world of lambda calculus. It is a lambda expression which is *basically* a function. *How do you represent a function as a string?*

But since `addSix` is a lambda that behaves exactly like a function, you can apply `addSix` to an integer and it will give you a meaningful answer.

```haskell
ghci> addSix 3
9
```

In fact you can even omit the part where you bind the value returned by `addMaker` to a name, and instead use it directly. Here, `(addMaker 7)` is a lambda expression and therefore it can be applied to an integer.

```haskell
ghci> (addMaker 7) 4
11
```

This nifty trick right here is the reason why lambda expressions are also called **anonymous functions** since these expressions on their own don't have a name. Lambda expressions generally appear in functional programing languages and even non-strictly functional programming languages. Lambdas can be useful if you want to create a function that will be used only once:

```haskell
ghci> applytwice (\x -> x + 2) 3
7
ghci> (\x -> x * x) 4
16
```

Lambdas, just like any other canonical value type can be bound to identifiers. Doing this will **name** the lambda thus allowing it to behave just like any other named function. 

> By the way, bindings are haskell's representation of a mathematical let statement. When you see an `=` operator like the following:
>
> ```
> x = 3
> ```
>
> This is not an assignment statement, but instead a let binding. **3** is bound to the identifier `x`. Similar to what happens when you say $\text{let } x = 3$ in math.

###### Closure

A higher order function like `addMaker` above, is not only producing the lambda inside its definition. What is actually being produced is a construct called a **closure** which is the function definition described by the lambda and the environment of the function call. The extra data, called environment, is the reason why the lambda `(\y -> x + y)` makes sense outside the context of `addMaker`. Without passing the environment the variable `x` would be a free variable which will yield you a compilation error. 

Inside your `addmaker` when you evaluate `addmaker 6` , the parameter `6` is bound to the variable $x$. So the resulting lambda that is produced will behave exactly like the lambda `(\y -> 6 + y)`.

Closures are still a direct consequence from lambda calculus' variable binding rules. Specifically how the variables $x$ and $y$ are bound in innermost body of the lambda calculus abstraction $\lambda x.\lambda y. addx$.

###### Multiple parameters and partial application

If we look back to lambda calculus you'll notice how abstractions are defined to be:
$$
\lambda x. \mathscr{M}
$$
Here we can see that abstractions are defined to have exactly one parameter. One can argue that this is different from the how functional programming represents its own functions and lambdas since functions with multiple parameters are allowed in these languages. Actually these functions are just disguised to have multiple parameters. These functions are just several single parameter functions combined to simulate multiple parameter functions. As an example: a function `add` that adds two numbers may look like multiple parameter functions:

```Haskell
plus x y = x + y
```

But internally this function is equivalent to two lambda calculus abstractions, nested together to simulate multiparameterness.

```haskell
plus = \x -> (\y -> x + y)
```

Here plus is a higher level function that accepts a single argument `x` and produces the lambda expression (to be perfectly correct it is returning a closure) `(\y -> x + y)`. This expression is a direct implementation of the following lambda calculus abstraction:
$$
\text{plus}=\lambda x.\lambda y. \text{add }x y
$$
Just like lambda calculus Haskell's `->` operator is right associative so you can write the same plus function as:

```haskell
plus = \x -> \y -> x + y
```

You can even omit the first `->` and the `\` near `y` and it will mean the same lambda expression:

```haskell
plus = \x y -> x + y
```

Which looks almost exactly similar to a relaxed lambda calculus expression with multiple parameters:
$$
\lambda xy.\text{add }xy
$$
Now when you want to apply this function we write:

```haskell
ghci> (plus 3) 4
7
```

Which means that first we are evaluating `(plus 3)` which will give us a lambda expression. The lambda expression is then applied to `4` which completes the evaluation to `7`. This is also a direct implementation of a lambda calculus application:
$$
(\text{plus }\overline{3}) \overline 4
$$
And just like lambda calculus, function applications in Haskell are also left associative so you can omit the parentheses:

```haskell
ghci> plus 3 4
7
```

All multiple parameter functions and lambdas in Haskell are nested single parameter abstractions in disguise, so for all intents and purposes these two definitions for `plus` behave in the exact same way:

```haskell
plus x y = x + y
```

```haskell
plus = \x -> (\y -> x + y)
```

This means that the expression`(plus 3)` will have the same meaning regardless of the way you define `plus`. The expression `(plus 3)` has a special name, it is called a **partial application**. When you apply a function that is supposed to accept $n$ parameters to $m$ values (where $m<n$) (what this means is that you're supplying the function less parameters than it is expecting), instead of getting the value, you get a partial application of that function which will evaluate to a lambda expression.

The process of converting a multi parameter function or lambda to a nested single parameter lambda is called **currying**. This term is named after the mathematician Haskell Brooks Curry, which is the same Haskell, the programming language is named after.

### Functional purity and the absence of states

One of the most distinguishing aspects of functional programming and the declarative family of languages is its philosophy of statelessness. A programmer primarily exposed to mutable imperative programming languages will find that the concept of state is natural and maybe even inevitable. Functional paradigm challenges this concept and offers a much safer and mathematically intuitive philosophy. In the perspective of functional programming, there is no state, and everything is immutable.

#### Pure functions

To understand the functional programming perspective, we have to take a step away from the imperative programming definition of a function. Let's go back to the definition of a function in mathematics.

Across several, mathematical disciplines a function means the same thing. Consider two sets $A$ and $B$. we can define a function as the mapping between the elements of $A$ and $B$. The elements of $A$ and $B$ can be anything, they can be numbers, which shows us how a function can be represented by a formula or a graph. The elements of $A$ and $B$ can be matrices and vectors, which defines a function as a transformation between two vector spaces. On the higher level perspective of category theory, functions are morphisms between objects of a given category. At its core a function basically defines arrows between things. 
$$
f:(A\to B)
$$
If you remember functions from discrete math, functions at its most basic form looks like the one above.

Functions in functional programming languages like Haskell are (arguably) the closest computer representation of a mathematical function. We call these functions, **pure functions**.

They differ from your standard C function because the definition inside pure functions are only instructions on how to produce a result based on the parameters. To fully understand this concept here are some examples of impure functions

```c
int square(int x){
	addToExternalLogger("calculating square");
 return x*x;
}
```

The impurity in this function is the line where the function writes to some external logger, `addToExternalLogger("calculating square");`. A function can only be pure if the result of the function can be fully determined by its parameter. The only parameter here is `x`. Invoking this square function with the same parameter value does not do the same thing. The effect of changing the logger is dependent on the previous state of the external logger. The effect on the logger is what we call a **side-effect** of the `square` function. It is a side effect since this line of code modifies values outside boundaries of the function.

```c
int* increaseArray(int *a, int size){
 for(int i = 0; i < size; i++)
  a[i]+1;
}
```

A pass by address/reference function which changes the value of a parameter will automatically be an impure function since changing the value of `a` is a **mutation**, which is a side-effect. 

```java
String headsortails(){
	if(randInt()%2==0)
		return "heads";
	else
		return "tails";
}
```

This function is also impure because the return value is not dependent on the parameters alone. The return value will be dependent on the randomization seed which is something outside the parameters of the function.

A pure function must satisfy these two:

1. A pure function has no side-effects
2. A pure functions output must be dependent on the inputs alone

> In fact if $f(a)=b$ and $f(a)=c$ where $b\neq c$ is not a function at all

A good way to test if a function is pure is if you can can (theoretically) create an infinitely long lookup table such that looking up the value for a specific input is perfectly identical to calling the function with the same input. And if you think about it this is the essence of a function. It is a list of associations between the domain and the range.

#### The Absence of mutation

One of the hallmarks that make imperative programming imperative is the assignment statement. It enables the program to advance to a new state. Purely functional programming languages like Haskell, lacks the mechanism to mutate anything. Using the "`=`" operator (which signals an assignment statement in imperative languages) in functional languages binds the value on the right hand side to the left hand side. This mechanism is conceptually different from an assignment operation in C. For example:

```C
int x = 0;
x = 1;
```

This code in C starts with a combined declaration and assignment: `int x = 0` . The second line then, **reassigns** the same variable `x` to the new value `1`. These lines of code corresponds to a mutation on the variable `x`, (from `0` to `1`). The value of the variable `x` is not definite since it can change within the runtime of the program. Because of the existence of mutation, the values of variables are dependent on the current **state** of the program.

Replicating this C code in Haskell is in fact not allowed:

```haskell
x = 0
x = 1
```

It will give an error message upon compilation:

```haskell
main.hs:2:1: error:
  Multiple declarations of ‘x’
  Declared at: main.hs:1:1
         main.hs:2:1
```

In Haskell, any "`=`" is a declaration of a binding. In fact all declarations in haskell are required to be **bound **to a value. These bindings are final (*in the scope of the identifier*). It is even wrong to call `x` here a variable since its value does not vary. The correct way to call `x` is identifier, since it merely identifies the value bound to it. 

#### Consequences of Statelessness and Immutability

Although Haskell's functions are our best representation of a mathematical function, this does not mean that it is pragmatically better than the classic impure functions of C. Restricting a programming language against side-effects seems like an artificial disadvantage introduced only to faithfully implement mathematical functions. 

There are several reasons why programming without state is a better way of programming. Eliminating all side-effects is demonstrably safer against accidental errors. Building a library of functions perfectly working without worrying about side-effects makes the system easier to understand and more resilient to changes.

```c
void f(int *x, int y){
  *x = *x + y;
  printf("%d\n",*x);
  return;
}
int main(void) {
  int x = 0;
  f(&x, 3);
  x = x - 2;
  f(&x, 3);
}
```

The exact behavior of the function `f` depends on where you call it. Even if you use the same parameters, you're not guaranteed to get the same results.

On small chunks of code, managing the consequences of having states such as global variables, will be trivial since you can reasonably track which variables are global (*or external in general*) and which functions interact with the global variables. (Even this tiny amount of code, the function's effects and side effects are not very obvious).

As the system grows, using functions and variables without double checking for side effects becomes much harder. As a consequence the whole system becomes a nightmare of impure functions on top of impure functions which may unexpectedly affect other parts of the system. On a corporate setting where multiple people are working on the same system, refactoring becomes unsafe without knowledge of all the side-effects of the functions in use. On systems with shared resources and multi-threading it becomes extra-extra difficult to keep track of things without proper documentation.

Don't get this wrong, though. Even with all these disadvantages in the state-full mutable paradigm of imperative programming, one can still code robust and harmonious systems. You just have to be extra careful writing your code with discipline, only using globals and side-effects when it is safe and necessary (*this is in fact the reason why writing smaller pure functions is considered good practice in any paradigm*). After all, being able to code with states can be thought of as an extra feature. You can be a C programmer and just treat all your variables as immutable and all of your functions as pure. (*calling variables, immutable is kinda oxymoronic because variables are meant to change, but whatever*)

Functional programming has its own set of disadvantages as well, most of them related to this seemingly artificial crutch of statelessness and immutability. The most obvious one is that creating new values instead of changing an existing variable has extra overhead in both processing and memory, making functional programming slower and less efficient. Programming without state can be difficult to do for certain mechanisms (*Like the external logger for example*). Simulating mechanisms like this may introduce conflict on how you compose your functions. It's not impossible though, you just have to learn some category theory concepts such as **monads**. Also, for most people who are used imperative programming, recursion, does not feel natural compared to iteration. But in my opinion, after being exposed to functional programming for some time, recursion makes much more sense than iteration. 

Nevertheless, these disadvantages are not insurmountable. In fact, there are a plenty of systems written in functional programming languages running without issues. Functional programming has had the reputation of being more conceptual and fancy than the classic imperative programming language. People mocked Haskell for its pristine white tower "elitist" approach to programming. But recently these functional programming languages like Haskell, F# and Scala has enjoyed improvements that has elevated it to be as pragmatic as your classic C, C++ or Java. In fact, functional programming has gained a considerable rise in popularity to an extent that mainstream programming languages with imperative roots like C#, Python, and JavaScript have started to introduced features patterned from pure functional programming languages. (Features such as higher-order functions and lambdas). With the risk of sounding editorial I even argue that learning functional programming concepts has become a necessity for any programmer, regardless of his/her paradigm preferences.\

[^1]: Church, A. (1932). A Set of Postulates for the Foundation of Logic. *Annals of Mathematics,* *33*(2), second series, 346-366. doi:10.2307/1968337

[^2]: Steele, A. (1996). The Evolution of Lisp. *History of programming languages—II*, 233-330. doi:[10.1145/234286.1057818](https://doi.org/10.1145/234286.1057818)

## Optional Readings

Lambda Calculus Encodings