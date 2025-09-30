# Exceptions (Kotlin)

## Introduction

One of the features added to imperative programming was the elegant handling of errors. Exception handling provided OOP a mechanism to control what exactly the system does when parts of the system fail. 

## Learning Outcomes

1. Create methods that throw errors in kotlin
2. Create a try-except clause to properly react to errors in kotlin
3. Design systems that correctly assign error handling responsibilities

---

Let's explore this with an example. In the snippet below, the last line is not reached because the system fails during `println(quotient(3f, 0f))`.

```kotlin
fun quotientUnsafe(a: Int, b:Int): Int {
    return a/b
}

fun main() {
    println(quotientUnsafe(5, 2))
    println(quotientUnsafe(3, 0))
    println("continuation")
}
```

```
2
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at ExceptionsKt.quotientUnsafe(exceptions.kt:7)
	at ExceptionsKt.main(exceptions.kt:48)
	at ExceptionsKt.main(exceptions.kt)
```

> Kotlin has its own exception thrown when it encounters division by zero (`ArithmeticException`) but we'll create our own for the sake of learning.

Problematic functions and methods like `quotientUnsafe` don't always return a float. The problem for this `quotientUnsafe` function is that there is a possibility you'll end up dividing with zero. This introduces the concept of exceptions. Where the quotient function works **except** when the denominator is zero.

To implement this kind of behavior. You can create an if-else check (or any control statement) to make sure the denominator is not zero. If you do encounter a zero denominator you **`throw`** an error. Here you are throwing a user defined error object called `DivisionByZeroError`. This user defined exception must be defined to be a specialization of the built in class `Exception`.

```kotlin
class DivisionByZeroError(message: String) : Exception(message)

fun quotient(a: Float, b: Float): Float {
    when (b) {
        0f -> throw DivisionByZeroError("Division By Zero: $a/$b")
        else -> return a/b
    }
}
```

When quotient is called where the denominator passed is zero, python will inform you that the function call has resulted in a `DivisionByZeroError`

```kotlin
fun main() {
    println(quotient(5f, 2f))
    try {
        println(quotient(3f, 0f))
    } catch (ex: DivisionByZeroError) {
        println("division by zero")
    }
    println("continuation")
}
```

```
2.5
division by zero
continuation
```

By enclosing the lines of code that could potentially throw errors with a `block` you create a safety net for the system. The system **tries** to execute these lines, and if there are no errors thrown inside the `try` block then the system runs normally. Nothing abnormal would occur **except** when the problematic lines of code throws an error. In this specific case, the system is **catching**, a specific type of error called `DivisionByZeroError`. If a specific type of `Exception` is not specified in the exception block, the system will catch any `Exception` specialization.

If a method with potential to throw errors like `quotient()`, is invoked inside another method without using the try except block, the caller method becomes a method with potential to throw errors as well. 

```kotlin
fun mixedFraction(wholePart: Int, numerator: Int, denominator: Int): Float {
    return wholePart + quotient(numerator.toFloat(), denominator.toFloat())
}

fun main() {
    println(mixedFraction(1,1,0))
    println("continuation")
}
```

```
Exception in thread "main" DivisionByZeroError: Division By Zero: 1.0/0.0
	at ExceptionsKt.quotient(exceptions.kt:12)
	at ExceptionsKt.mixedFraction(exceptions.kt:18)
	at ExceptionsKt.main(exceptions.kt:47)
	at ExceptionsKt.main(exceptions.kt)
```

Here, the `quotient()`'s caller,`mixedFraction()`, does not enclose the problematic line with a try-except block. This means that `mixedFraction` is basically the ignoring any potential error, shifting the responsibility of dealing with the error to wherever `mixedFraction()` is called.

On the example below, `quotient()` is invoked inside `quotientString()`, but instead of ignoring the error, `quotientString()` deals with it using a try-except block. When quotient fails, the function returns "undefined number" instead of a fraction string.

```kotlin
fun quotientString(a: Float, b: Float): String {
    try {
        val quotient = quotient(a,b)
        val wholePart = floor(quotient(a,b))
        return "whole part: $wholePart, decimal part: ${quotient - wholePart}" 
    } catch (ex: DivisionByZeroError) {
        return "undefined number"
    }
}

fun main() {
    println(quotientString(17f,7f))
    println(quotientString(1f,0f))
    println(quotientString(1f,3f))
}
```

```
2 and 0.4285714285714284
undefined number
0 and 0.3333333333333333
```

By dealing with potential errors, `quotientString()` becomes a safe function that has no potential of throwing `DivisionByZeroError`.

You can catch multiple kinds of exceptions if you want to handle different exceptions in different ways. Here, you see different kind of `Exception` called `IndexOutOfBoundsException`. This exception is thrown when an indexed collection like `List` accesses a non-existent member. Here the function `quotientList` wants to append `Float.POSITIVE_INFINITY` if you're dividing by zero and not append anything if you're dividing with non-existent list members.

```kotlin
fun quotientList(l: List<Float>, m: List<Float>): List<Float> {
    val r: MutableList<Float> = mutableListOf()
    for (i in 0..(l.size-1)) {
        try {
            r.add(quotient(l[i], m[i]))
        } catch (ex: DivisionByZeroError) {
            r.add(Float.POSITIVE_INFINITY)
        } catch (ex: IndexOutOfBoundsException) {
            //do not append anything to the list
        }
    }
    return r.toList()
}

fun main() {
    println(quotientList(listOf(1f,2f,3f,4f,5f,6f), listOf(3f,0f,2f,2f,1f)))
}
```

```
[0.33333334, Infinity, 1.5, 2.0, 5.0]
```

### To ignore errors or to deal with errors?

So you've seen two ways to react with potential errors, to ignore them like `mixedFraction()` or to deal with them like `quotientString()`. Which is the proper way? You might think that dealing with errors is better since it's this way doesn't break the system. But actually the choice to ignore or to deal with errors depends on who has the correct responsibility in fixing the error. 

If you immediately deal with the error as quickly as possible, you'll end up missing the importance of raising errors. The method `quotient()` for example, is responsible for providing the caller with a quotient, that must be this method's only responsibility. You should not give `quotient` the responsibility of fixing division by zeros. That responsibility lies on its caller, because different caller's may have different ways to deal with the error. The method `quotientString()` deals with division by zero with "undefined number" while the method `quotientList()` deals with division by zero with $\infty$. These two callers have different ways of interpreting division by zero so they should be the one's responsible for dealing with the error.



