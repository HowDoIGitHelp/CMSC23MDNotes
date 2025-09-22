# Kotlin Introduction

Kotlin is a high-level language with rich syntax support. Lets start by discussing how statements and expressions in kotlin look like. Unlike C or C++, statements in kotlin do not end with a semicolon. The boundary of a statement is defined by newlines. Below you have two statements


```kotlin
3 + 5
9 - 2
```


   7



# Kotlin types

### Numerical types

Numbers in kotlin can be `Int`s `Floats`, `Long`, `Double` and more. An `Int` literal can be written just like any number in math.


```kotlin
3
```


   3



You can expose the specific type of an expression using the following syntax:


```kotlin
3::class.simpleName
```


   Int




```kotlin
(2 - 3)::class.simpleName
```


   Int



A number with decimal values are automatically interpreted as `Double`


```kotlin
3.1::class.simpleName
```


   Double




```kotlin
2.0::class.simpleName
```


   Double



If you want to force kotlin to interpret a number literal as `Long`, add the prefix `L` to the number literal. If you want to force kotlin to interpret a decimal literal as `Float`, add the prefix `f`.


```kotlin
2L::class.simpleName
```


   Long




```kotlin
0xffffffffL::class.simpleName //works with hexadecimal values too
```


   Long




```kotlin
0.0f::class.simpleName
```


   Float




```kotlin
(-1f)::class.simpleName
```


   Float



### Boolean values

You can write `Boolean` literals using `true` and `false`. Expression that evaluate into either `true` or `false` are also `Boolean` values


```kotlin
true::class.simpleName
```


   Boolean




```kotlin
false::class.simpleName
```


   Boolean




```kotlin
(4 > 5)::class.simpleName
```


   Boolean



### Characters

Character literals are created by surrounding single characters with single quotes


```kotlin
'a'::class.simpleName
```


   Char




```kotlin
'2'::class.simpleName
```


   Char




```kotlin
'\n'::class.simpleName
```


   Char



### Operations and type compatibility

When combining different types using operations, only some combinations of types are compatible for said operation. Test the following code on your own to see how kotlin handles them:


```kotlin
3 + 4.0
```


   7.0




```kotlin
'2' + 1
```


   3




```kotlin
1 + '4'
```


    org.jetbrains.kotlinx.jupyter.exceptions.ReplCompilerException: at Cell In[64], line 1, column 3: None of the following functions can be called with the arguments supplied:...


   

```kotlin
true + 0
```


    org.jetbrains.kotlinx.jupyter.exceptions.ReplCompilerException: at Cell In[65], line 1, column 6: Unresolved reference. None of the following candidates is applicable because of receiver type mismatch: 


​    


### Values and Variables

Just like other languages, you can assign values or into identifiers. There are two types in kotlin, values (denoted by `val`) and variables (denoted by `var`).

Variables are just like any variables in the imperative sense. You can assign values to it and you can reassign new values to it. When declaring `var`s (and `val`s) you must provide it with an assigned value. Without an assigned value, kotlin cannot compile


```kotlin
var x = 0
x = 1
```


```kotlin
var y
```


    org.jetbrains.kotlinx.jupyter.exceptions.ReplCompilerException: at Cell In[43], line 1, column 1: Abstract property 'y' in non-abstract class 'Line_42_jupyter'


​    


The value assigned to a `var` or `val` allows kotlin to **infer** the type of said `var` or `val`.


```kotlin
var y = 2
y::class.simpleName
```


   Int



You can also explicitly declare the type of a `var` or `val` using the following syntax. 

In some cases kotlin cannot infer the type of a `var` or `val`. This happens when the assigned value is ambiguous or a value cannot be provided (function declarations and abstractions). For these cases, an explicit type definition is required.


```kotlin
var y: Int = 2
```

Kotlin `val`s are just like `var`s except the values assigned during declaration are final. Kotlin `val`s cannot be reassigned with new values.


```kotlin
val z = 5
z = 4
```


    org.jetbrains.kotlinx.jupyter.exceptions.ReplCompilerException: at Cell In[49], line 2, column 1: Val cannot be reassigned


​    


# Compound Types

Compound types are types that are made of other types.

### Pair

A `Pair` in kotlin is a pair of values. You can create a pair literal using the following syntax:


```kotlin
1 to 5
```


   (1, 5)



You can access the first value and second value using `first` and `second`


```kotlin
val point = 2 to 5
point.first
```


   2




```kotlin
point.second
```


   5




```kotlin
val u = true to 'a' // it can be a mix of any two types
u.first
```


   true



you can also use destructuring to separate a pair into two `var`s or `val`s using the following syntax:


```kotlin
val (booleanValue, charValue) = u

booleanValue
```


   true




```kotlin
charValue
```


   a



# Collections

Collections in kotlin are like specialized arrays. They can hold a series of values and include useful member functions and attributes. 

### List

You can create a list using the builtin `listOf` constructor. The code below declares a variable called number assigned with the list containing the integers `1`, `2`, `3`, and `4` as its elements.


```kotlin
var numbers = listOf(1,2,3,4)
numbers
```


   [1, 2, 3, 4]



When creating an empty list `var` or `val`, kotlin cannot infer the type of the elements of the said empty list. This is a case where you must explicitly declare the type of the of the variable. The syntax below declares that you are creating an empty list of `Int`s


```kotlin
var emptylist: List<Int> = listOf()
emptylist
```


   []



You can check if an element is a member of a Collection using the `in` operator


```kotlin
3 in numbers
```


   true




```kotlin
5 in numbers
```


   false



You can concatenate two lists using the `+` operator (as long as the types match)


```kotlin
listOf(1,2,3,4) + listOf(5,6,7)
```


   [1, 2, 3, 4, 5, 6, 7]



The following are useful builtin functions and attributes for lists:


```kotlin
numbers.size //attribute where the size (number of elements) of the list is stored
```


   4




```kotlin
numbers.elementAt(2) //returns the element at index 2 (third element)
```


   3



You can also achieve the behavior of `elementAt` at using list indexing:


```kotlin
numbers[2]
```


   3



Lists in kotlin are immutable. This means that you cannot change the elements inside it.


```kotlin
numbers[2] = 4
```


    org.jetbrains.kotlinx.jupyter.exceptions.ReplCompilerException: at Cell In[9], line 1, column 1: Unresolved reference. None of the following candidates is applicable because of receiver type mismatch: 
    


​    


But since `number` is a `var`, even if the current list stored inside it is immutable, `number` itself is a variable, meaning `number` itself can be changed.


```kotlin
numbers = listOf(5,6,7,8)
numbers
```


   [5, 6, 7, 8]



### MutableList

Mutable lists can be created using the `mutableList()` constructor. Like the name suggests, the elements of this type of collection can be changed.


```kotlin
var series = mutableListOf(-1,-2,-3,-4)
series
```

You can change the elements by reassigning elements accessed through element indexing:


```kotlin
series[1] = 5
series
```


   [-1, 5, -3, -4]



You can append elements to the list using the function `add()`


```kotlin
series.add(-5)
series
```


   [-1, 5, -3, -4, -5]



You can remove elements using `remove()` (removes the fires occurence of the reference passed in the function) or `removeAt()` (given an index, it removes the lement at said index)


```kotlin
series.remove(-3) //removes the first occurence of -3
series
```


   [-1, 5, -4, -5]




```kotlin
series.removeAt(1) //removes the element at index 1
series
```


   [-1, -5]




```kotlin
numbers.slice(0,1)
```


    org.jetbrains.kotlinx.jupyter.exceptions.ReplCompilerException: at Cell In[49], line 1, column 15: The integer literal does not conform to the expected type IntRange


​    


### IntRange and IntProgression

`IntRange`s are collections of series of `Int`s. Using `IntRange`s you can generate a finite series of integers by simply defining the start and finish,


```kotlin
var range = 0..10
range
```


   0..10




```kotlin
range.elementAt(0)
```


   0




```kotlin
range.elementAt(2)
```


   2




```kotlin
range.toList() // We reveal the elements of the range by generating a list out of it
```


   [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]



When specifying a step size you will create an `IntProgression` instead. This will allow you to skip some integers 


```kotlin
(-3..4 step 2).toList()
```


   [-3, -1, 1, 3]




```kotlin
(10..45 step 4).toList()
```


   [10, 14, 18, 22, 26, 30, 34, 38, 42]



You can use `IntRange`s to create sublists out of `List`s and `MutableLists`. Using the builtin `slice()` function you can generate a new list taking only elements specified in the `IntRange` instance passed in `slice()`.


```kotlin
val aList = listOf(101,102,103,104,105,106,107,108,109)
aList.slice(0..3) // creates a sublist from index 0 until index 3
```


   [101, 102, 103, 104]




```kotlin
aList.slice(5..(aList.size-1)) //creates a sublist from index 5 until the last available index
```


   [106, 107, 108, 109]




```kotlin
aList.slice(8 downTo 0 step 2) // to create a decreasing progression use `downTo` instead of `..`
```


   [109, 107, 105, 103, 101]



## Strings

`Strings` are special collections where the elements are exclusively `Char`s. Just like in C, `String`s are used to represent text. You can create a `String` by surrounding characters with double quotes.


```kotlin
var str = "this is a string"
str
```


   this is a string




```kotlin
str::class.simpleName
```


   String



You can use a lot of the existing builtin list functions and operations for strings as well


```kotlin
str.length // str uses the attribute length as the number of elements
```


   16




```kotlin
str[2] // accessing individual elements
```


   i




```kotlin
str + " too." // concatenation
```


   this is a string too.




```kotlin
'h' in str
```


   true




```kotlin
"is a" in str
```


   true



Just like `List`s, `String`s are also immutable


```kotlin
str[0] = 'b'
```


    org.jetbrains.kotlinx.jupyter.exceptions.ReplCompilerException: at Cell In[123], line 1, column 1: Unresolved reference. None of the following candidates is applicable because of receiver type mismatch: 


​    


You can print strings to the standard output stream using the buitlin functions `print()` and `println()`, the only difference between these two is that `println()` automatically adds a linebreak (`\n`)


```kotlin
print("this ")
print("is")
println(" a")
println("string")
```

    this is a
    string


Using string templates you can easily insert non-`String`s expressions into `String` literals. You can use the `${}` syntax to achieve this:


```kotlin
"Sum is: ${3 + 2}"
```


   Sum is: 5



Expressions enclosed inside `${}` are evaluated, converted into `String` representations, and inserted into the string. When inserting `var`s or `val`s you can omit the curly braces:


```kotlin
val list = listOf(1,2,3)

"List elements:$list, size:${list.size}"
```


   List elements:[1, 2, 3], size:3



### Map

`Map`s are collections of `Pair`s. This datatype models the mapping between two values. In each `Pair` entry, the first value is called the key. You can think of a map as an array but instead of using integers as indices you can use any type as indices. The keys of a map are basically its indices.


```kotlin
val m = mapOf('a' to 1, 'b' to 2, 'x' to -1)
m
```


   {a=1, b=2, x=-1}




```kotlin
m::class.simpleName
```


   LinkedHashMap



When dereferencing using the index operator you can access the each key's associated value.


```kotlin
m['a']
```


   1




```kotlin
m['x']
```


   -1



Just like lists you can concatenate two maps using the `+` operator (as long as the types of match). You can also use `size` to find the number of pairs, and `in` to check if a key exists in the map


```kotlin
m + mapOf('y' to -4)
```


   {a=1, b=2, x=-1, y=-4}




```kotlin
m.size
```


   3




```kotlin
'x' in m
```


   true



`Map`s are immutable. The entries cannot be changed. If you want a mutable version you can instead use a `MutableMap`:


```kotlin
val foodtype = mutableMapOf(
    "apple" to "fruit",
    "tomato" to "vegetable",
    "carrot" to "vegetable",
)
// the formatting above is optional, it helps with readability

val food = "apple"
foodtype[food]
```


   fruit



Since `foodtype` is mutable. You can add change entries using assignment:


```kotlin
foodtype["tomato"] = "fruit"
foodtype
```


   {apple=fruit, tomato=fruit, carrot=vegetable}



If you assign on an non-existent key, kotlin will create a new instance with said key:


```kotlin
foodtype["soy sauce"] = "condiment"
foodtype
```


   {apple=fruit, tomato=fruit, carrot=vegetable, soy sauce=condiment}



you can delete using `remove()`


```kotlin
foodtype.remove("carrot")
foodtype
```


   {apple=fruit, tomato=fruit, soy sauce=condiment}



# Selection and Repetition

Selection and repetition in kotlin is written similar to c's selection and repetition.

### `if`-`else`


```kotlin
if (1 > 3) 
    print("foo")
else
    print("bar")
```

    bar


```kotlin
if (1 > 2) {
    print("branch 1")
}
else if (0 == 0 && 'a' == 'a') {
    print("branch 2")
}
else {
    print("branch 3")
}
```

    branch 2

### Ternary

Kotlin also supports a ternary expression. A ternary expression can evaluate to either one of two possible expressions. If the condition on the ternary expression evaluates into `true` then the entire ternary expression will evaluate into the first expression, and the second expression otherwise.


```kotlin
val number = -2

3 + (if (number > 0) number else (-number))
```


   5



You can also use other `selection` patterns in kotlin using `when` clauses: [kotlin control flow](https://kotlinlang.org/docs/control-flow.html)


```kotlin
var sum = 0
var i = 0 
while (i < 5) {
    sum += i
    i++
}
sum
```


   10




```kotlin
do {
    print("this")
} while (false)
```

    this

### `for` loop

A `for` loop in kotlin is a different from for loops in c. Instead of supplying it with the usual initialization-condition-increment, you instead supply it with the membership check operation, `in`, specifically checking if a given `var` (`iter` in the example below) is a member of a given collection (`listOf(1,2,3,4,5)`). If true then kotlin executes enclosed block. The loop initializes `iter` with the first element of the collection (`1` in the example below). After executing one iteration it then reassigns the `iter` to the next element (`2` in the example below) and loops back. It does this until it goes through every element of the collection exactly once.


```kotlin
for (iter in listOf(1,2,3,4,5)) 
    print("$iter,")
println()
```

    1,2,3,4,5,


For loops work with any collection (and other types that have iterators), including `Map`s:


```kotlin
val entries : Map<Char,Int> = mapOf('a' to 20, 'b' to 5, 'c' to 30) 

var cumulativeSum = 0
for ((key,value) in entries) { // since elements of a map are pairs, it can be destructured using the syntax here
    cumulativeSum += value
    println("$key : $value, $cumulativeSum")
}
```

    a : 20, 20
    b : 5, 25
    c : 30, 55


You can achieve iteration over a range of numbers using when combining for loops with `IntRange` and `IntProgression` collections:


```kotlin
for (i in 0..6)
    print("$i,")
```

    0,1,2,3,4,5,6,


```kotlin
for (i in 10 downTo -10 step 3)
    print("$i,")
```

    10,7,4,1,-2,-5,-8,

# Functions

You can define functions in haskell using the `fun` declaration:


```kotlin
fun printSquare(x: Int) {
    print("square of $x is ${x*x}")
}

printSquare(25)
```

    square of 25 is 625

As seen above, when defining functions, kotlin cannot infer the type of the parameters. You must declare parameters with an explicit type.

The `printSquare()` function does not return anything. But internally it actually returns a type known as a `Unit`. It's a type that can only have one value. When you omit the return type of a function, kotlin automatically assumes that the return type is `Unit`. 

When creating functions that return something, you must declare the return type:


```kotlin
fun square(x: Int): Int {
    return x * x
}

square(25)
```


   625



### Optional parameters

Kotlin functions can be declared with optional parameters. For a parameter to be designated optional, it must be supplied with a default value. In the example `introduce()`, we designate the parameter `isCensored` as optional by appending `= true`.


```kotlin
fun introduce(name: String, age: Int, isCensored: Boolean = false) {
    if (!isCensored)
        println("$name, $age")
    else
        println("redacted")
}
```

When invoking `introduce()` you can omit `isCensored`, when doing so you `isCensored` will be automatically assigned with the its default value, `true`


```kotlin
introduce("Rub", 75)
```

    Rub



```kotlin
introduce("Rub", 75, true)
```

    redacted


### Named parameters

When calling functions, you can rearrange the parameter order by explicitly naming parameters using the following syntax


```kotlin
introduce(age = 90, name = "Rub")
```

    Rub, 90


# Null Safety

By default, all types in kotlin cannot have a `null` value. We call these types non-nullable. Kotlin can infer if a specific `var`, `val`, parameter, or function return has the potential to be `null`. In the example below, kotlin infers the value `nullable` as a nullable integers. This is because if database is indexed with a nonexistent key (like `"invalid"`), then it cannot supply a value. As a result `database[q]` has a potential to evaluate into `null`


```kotlin
val database = mapOf("this" to 1, "that" to 2)


var q = "invalid"
val nullable = database[q]
```


```kotlin
nullable
```


   null



On cases where you need to declare that a type is nullable suchas the function return value below. You can append the type with a `?`:


```kotlin
fun value(map: Map<String,Int>, key: String): Int? { // removing the `?` will result in a compilation error
    return map[key]
}
```

A nullable type will cause type mismatch errors on some operations. In the example below, `+` wont work with nullable values. Even if the expression does not evaluate into null. The code below wont even compile:


```kotlin
value(database,"this") + 3
```


    org.jetbrains.kotlinx.jupyter.exceptions.ReplCompilerException: at Cell In[296], line 1, column 24: Operator call corresponds to a dot-qualified call 'value(database,"this").plus(3)' which is not allowed on a nullable receiver 'value(database,"this")'.
    


​    


To remedy this you can do a null check using selection statements:


```kotlin
val v = value(database, "this")
if (v == null)
    print(v + 3)
else
    print(0 + 3)
```

    3

You can also use the specialized safe elvis operator `?:` that works like a ternary. Using this operator if the left operand evaluates into `null` the elvis operation evaluates into the left operand. Otherwise, it evalutes into the right operand.


```kotlin
(value(database, "this") ?: 0) + 3
```


   4




```kotlin
(value(database, "invalid") ?: 0) + 3
```


   3



You can also avoid returning nullable types by ensuring null safety in the function call. 


```kotlin
fun value(map: Map<String,Int>, key: String): Int { // removing the `?` will not result in a compilation error
    return map[key] ?: 0
}
```

# Imports and Packages

To import definitions from external packages you can use the import statement. The line below imports the class `File` found in the package `java.io`.


```kotlin
import java.io.File

val f = File("test.in")
```

You can also give imported definitions aliases. This will help when importing definitions from different packages that happene to have the same name:


```kotlin
import java.io.File as FileClass

val g = FileClass("test.in")
```

If you want to import everythin on the package `java.io`, you can use the following syntax


```kotlin
import java.io.*

val h: InputStream? = null // able import InputStream by importing everything
```

One way to make definitions available across different files is by using a `package` declaration above your .kt files

```kotlin
package library
...
```

All of the definitions (classes, functions global variables) in every `.kt` file with the package header `library` will be included. This allows you to use defintions across different files as long as they are in the same package

# File writing and reading

You can read and write files using the the class `File` in the `java.io*` package.


```kotlin
import java.io.File

val inputFile = FileClass("input.in")
```

You can read the entire file using `readText()`


```kotlin
inputFile.readText()
```


   content
    more content
    foo
    bar



You can read the entire file line by line using `readLines()` which stores the contents into a `List<String>` of lines:


```kotlin
for (line in inputFile.readLines())
    println("line: $line")
```

    line: content
    line: more content
    line: foo
    line: bar


You can write to a file using `writeText()`


```kotlin
val output = File("output.out")

output.writeText("foo")
```

Calling `writeText()` again will replace the current contents.


```kotlin
output.writeText("bar")
```

To append, you can use `appendText()` instead


```kotlin
output.appendText(" extra content")
```

