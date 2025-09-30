# Introduction

While kotlin is a modern multiparadaigm language. Its supports rich OOP support. In fact kotlin is very strongly OOP flavored. Every type in kotlin is a class and every value is an object.

# Kotlin Classes

Creating classes in kotlin is very easy, here's an empty class called `EmptyClass`:


```kotlin
class EmptyClass
```

> It is conventional to name your classes as nouns that start with capital letters like `EmptyClass`

To make classes more interesting, lets put something inside it. You can put methods and attributes inside classes. Everything found inside the `{ }` scope of a class, belongs to that class. Below we have a more interesting class called `VoiceBox`


```kotlin
class VoiceBox {
    fun speak() {
        println("Hi I'm a voicebox that speaks")
    }
}
```

Inside the scope of the class `VoiceBox` we have the function `speak()`. Since this function is a member of the class `VoiceBox` we also call it a **method**. We say `speak()` is a method of `VoiceBox`

To use this class we create an **instance** of `VoiceBox`. We invoke what is know as the **default constructor** of `VoiceBox` using the syntax `VoiceBox()`. The constructor `VoiceBox()` will evaluate into an instance of `VoiceBox`.

This constructed instance is assign to the `val` `v`. We call `v` an instance of `VoiceBox` or a `VoiceBox` **object**. To interact with this voicebox object we can call its method `speak()`. We use the dot-reference operator as shown below. This tells kotlin that we are invloking the method `speak()` specifically owned by the instance `v`.


```kotlin
val v = VoiceBox()
v.speak()
```

    Hi I'm a voicebox that speaks


**Classes vs objects**. Classes and objects are similar things. We refer to `v` as a `VoiceBox` object or instance. This is different from the `VoiceBox` class itself. The `VoiceBox` class refers to the definition of what `VoiceBox` objects like `v` can be. There can only be one `VoiceBox` class. On the other hand a `VoiceBox` instance like `v` refers to one of the many possible `VoiceBox` instances that can be created. In the example below we create another `VoiceBox` instance `w`. Right now there is no way to differentiate between these two because all `VoiceBox`es are created the same way. 

> The distinction between classes may not be important right now (especially in kotlin). But in some language you can create methods that are specifically owned by the class itself rather than the instances of the class. These methods are known as static methods. Kotlin does not happen to support static methods.


```kotlin
val w = VoiceBox()
v.speak()
```

    Hi I'm a voicebox that speaks


# Customizing the Constructor

All class definitions automatically come with a default constructor. But to add nuance to how instances are created we can change the constructor.


```kotlin
class Robot constructor (var name: String) {
    fun talk() {
        println("Howdy it's me $name")
    }

    fun communicate(partner: Robot) {
        println("Howdy ${partner.name} it's me $name")
    }
    
}
```

In the example above we change our constructor so that when invoked it accepts a string called `name` as a parameter. By adding `name` in the constructor header kotlin automatically creates an **attribute** called `name`. This attribute will recieve the value passed during the construction of `Robot`.

The attributes of a specific instance is owned by that specific instance. Using the code below as n example, the `Robot` instance `r1` has its own version of the attribute `name` (accessed through `r1.name()`) which is distinct from `r2`s version of `name`.

> Note how inside the function communicate, you refer to an external instance's name attribute using dot-reference (`partner.name`) while referencing an internal attribute is done directly.


```kotlin
val r1 = Robot("Bonk")
val r2 = Robot("Chonk")

println(r1.name)
println(r2.name)
```

    Bonk
    Chonk


Going back to the class definition of `Robot` you can see the attribute `name` referenced inside the scope of `Robot`. All attributes owned by `Robot` can referenced by any of `Robot`s methods. In fact it can be referenced anywhere inside the `Robot` scope.

When you call the methods `talk()` or `communicate()`, the reference `name` can be used without any problem.


```kotlin
r1.talk()
r2.communicate(r1)
```

    Howdy it's me Bonk
    Howdy Bonk it's me Chonk


Since `name` is declared as `var` in the constructor, it can be changed like any variable


```kotlin
r1.name = "Donk"
r1.talk()
```

    Howdy it's me Donk



```kotlin
class RoboticArm (val type: Char) { //if there are no visibility modifiers, the `constructor` keyword can be ommitted
    
    var position = 0.0f
    
    fun move(amount: Float) {
        position = position + amount
    }

    
    
    fun reset() {
        position = 0.0f
    }

}

val arm = RoboticArm('A')
println(arm.position)
arm.move(0.2f)
arm.move(0.5f)
println(arm.position)
```

    0.0
    0.7


In some cases you might want to add attributes to your classes without making said attributes as constructor parameters. To do this you simply declare the attribute somewhere inside the class but outside the method bodies. Just like the attribute `position` in the class `RoboticArm` above.

You can only place declarations and definitions in the class defintion scope. You cannot put runnable code. If you want to run some code during object construction you can create an `init` block. All of the code inside an `init` block will be executed during the constructor invocation.


```kotlin
class Camera {
    
    init {
        println("startup.")
        cleanSensor()
    }

    fun cleanSensor() {
        println("cleaning sensor...")
        println("cleaning complete")
    }

}
val cam = Camera()
```

    startup.
    cleaning sensor...
    cleaning complete


You can also **overload** the class's constructor in kotlin. To do this you simply define a new constructor block with a different set of parameters. The constructor in the class header (beside the class name) is called the primary constructor while all other constructors are know as secondary constructors.

You can read more aboth secondary constructors here: [Constructors](https://kotlinlang.org/docs/classes.html#constructors)


```kotlin
class Person (val name: String) {
    constructor (firstname: String, lastname: String) : this("$firstname $lastname")
}
```

# Kotlin Specializations and Realizations

All classes in kotlin inherit from the `Any` generalization. The `Any` class is a class that doesn't have any generalization.


```kotlin
println(r1 is Any) // r1 is an instance of Robot
```

    true


To establish your a specialization relationship in kotlin you must first mark the general class to be `open`:

```kotlin
open class Robot constructor (var name: String) {
...
```


```kotlin
open class Robot constructor (open var name: String) { //open is a modifier that allows Robot to be specialized
    
    open fun talk() {
        println("Howdy it's me $name")
    }

    open fun communicate(partner: Robot) {
        println("Howdy ${partner.name} it's me $name")
    }
    
}
```

To establish that  the class `Skybot` below is a specialization of `Robot`, it must be specified in class header using `:` syntax. In this particular example, `Robot` has a primary constructor (i.e. it is not using a default constructor), so `Skybot` must have a primary constructor with the same pattern as `Robot`. The primary constructor of `Robot` accepts one `String` called name, so `Skybot`s primary constructor should also accept one `String`. This must be done so that all of the attribute assignments in `Robot` are also inherited by `Skybot`. 


```kotlin
class SkyBot (name: String) : Robot(name) {
    fun fly(height: Int) {
        println("I'm flying ${height}m high in the air")
    }
}
```

Since `Skybot` is now a specialized `Robot`, it will inherit all of the inheritable definitions in `Robot`. This includes `talk()` and `communicate()`


```kotlin
val r = Robot("Donk")
val s1 = SkyBot("Zonk")

s1.talk()
s1.communicate(r)
```

    Howdy it's me Zonk
    Howdy Donk it's me Zonk


Due to polymorphism all specializations of `Robot` are also considered as `Robot`s. This means that `Skybot` instances can be used as `Robot`s without any type mismatch. The method `communicate()` which accepts `Robot` instances as parameters can also accept `Skybot` instances.


```kotlin
val s2 = SkyBot("Wonk")

s1.communicate(s2)
```

    Howdy Wonk it's me Zonk


Instances of `Skybot` can of course use it's specialized method `fly()`


```kotlin
s1.fly(4)
```

    I'm flying 4m high in the air


Specializations of classes can also override the definitions of their generalization. 


```kotlin
class ShadeBot(name: String, val visorOpacity: Float) : Robot(name) {
    
    override fun communicate(partner: Robot) {
        if (visorOpacity >= 1)
            println("Howdy, it's me $name. Sorry I cant see you my shades are too dark")
        else
            println("Howdy ${partner.name} it's me $name")
    }

}
```

To override a method or an attribute you must explicitly, use the `override` modifier. This tells kotlin that instead of inheriting `communicate()` you will replace it with the definition found in `ShadeBot`. Take note that methods are final by default so you can only override methods that are declared to be `open` in the generalization. Without the modifiers `override` and `open` the override of `communicate()` will not compile.

```kotlin
open class Robot constructor (var name: String) { 
    
    open fun talk() {... //talk() is actually not overriden right now but we can declare it to be open for future specializations

    open fun communicate(partner: Robot) {...
    
}
```

Also in the class header of `ShadeBot` we are overriding the primary constructor to accept two parameters instead of just one. Since `visorOpacity` is a special attribute of `ShadeBot` it has not been declared yet. Therefore we have to explicitly declare it as `val` or `var` in the in the primary constructor declaration. The attribute `name`, on the other hand, is inherited so it doesn't need to be redeclared. If you do redeclare it, then it will be considered as an override, requiring the `override` modifier.


```kotlin
val sh1 = ShadeBot("Tonk", 1.0f)
sh1.communicate(s1)
```

    Howdy, it's me Tonk. Sorry I cant see you my shades are too dark


## Realization

To create an abstraction in kotlin you simply use the keyword `interface`. Any abstraction's methods must be declared to be `abstract`. The `abstract` modifier allows these functions to have no body. Note that abstract functions are open by default so you dont need the `open` modifier


```kotlin
interface BorrowableItem {
    abstract fun borrow()
    abstract fun name(): String
}
```

To realize an existing abstraction, you also use the `:` syntax


```kotlin
class Book(val title: String) : BorrowableItem {
    
    override fun borrow() {
        println("I am a book called ${name()} and I'm being borrowed")
    }

    override fun name(): String{
        return title
    }
    
}

class IMacUnit(val id: Int) : BorrowableItem {

    override fun borrow() {
        println("I am an imac called ${name()} and I'm being borrowed")
    }

    override fun name(): String {
        return "iMac$id"
    }
}
```

Due to polymorphism, you can declare a `Book` or `IMacUnit` instance to be of type `BorrowableItem`.


```kotlin
val b: BorrowableItem = Book("Necronomicon")
val imac: BorrowableItem = IMacUnit(5)

b.borrow()
imac.borrow()
```

    I am a book called Necronomicon and I'm being borrowed
    I am an imac called iMac5 and I'm being borrowed



```kotlin
val binder: List<BorrowableItem> = listOf(b,imac)

for (bi in binder) 
    println(bi.name())
```

    Necronomicon
    iMac5



```kotlin
binder::class.simpleName
```


    ArrayList



Any class that realizes the `BorrowableItem` abstraction will be forced to override all of its abstract functions. If you miss overriding even one function, it will lead to an error


```kotlin
class Research: BorrowableItem{
    override fun name(): String {
        return "Research"
    }
}
```


    org.jetbrains.kotlinx.jupyter.exceptions.ReplCompilerException: at Cell In[25], line 1, column 1: Class 'Research' is not abstract and does not implement abstract member public abstract fun borrow(): Unit defined in Line_19_jupyter.BorrowableItem


​    


# Visibility Control

To implement proper data hiding principle sin OOP, kotlin has the visibility modifers `public`, `private`, and `protected`.


```kotlin
open class ClandestineClass (
    public var v1: Int,
    protected var v2: Int,
    private var v3: Int 
) { //the syntax above is just a way to make the formatted constructor look neater
    public open fun method1() {
        println("Hey, these are my values")
        println("$v1, $v2, $v3")
    }

    protected open fun method2() {
        println("Hey!")
    }

    private fun method3() {
        println("...")
    }

}

val secretiveObject = ClandestineClass(1,2,3)
```


```kotlin
secretiveObject.v1
```


    1




```kotlin
secretiveObject.v2
```


    org.jetbrains.kotlinx.jupyter.exceptions.ReplCompilerException: at Cell In[53], line 1, column 17: Cannot access 'v2': it is protected in 'ClandestineClass'




```kotlin
secretiveObject.v3
```


    org.jetbrains.kotlinx.jupyter.exceptions.ReplCompilerException: at Cell In[54], line 1, column 17: Cannot access 'v3': it is private in 'ClandestineClass'



As you see in the example above, accessing the attribute `v1` outside the scope of `ClandestineClass` works. On the other hand accessing the attributes `v2`, and `v3` will cause an error. This is because only `public` attributes can be accessed outside the scope of any give class.


```kotlin
secretiveObject.method1()
```

    Hey, these are my values
    1, 2, 3


The function body of `method1()` is inside the scope of the class, so there are no issues with using `v2`, and `v3` inside `method1()`.

Just like attributes, `protected` and `private` methods cannot be accessed outside the scope of the class.


```kotlin
secretiveObject.method2()
```


    org.jetbrains.kotlinx.jupyter.exceptions.ReplCompilerException: at Cell In[56], line 1, column 17: Cannot access 'method2': it is protected in 'ClandestineClass'


​    



```kotlin
secretiveObject.method3()
```


    org.jetbrains.kotlinx.jupyter.exceptions.ReplCompilerException: at Cell In[57], line 1, column 17: Cannot access 'method3': it is private in 'ClandestineClass'


​    

```kotlin
open class SpecialClandestineClass1(
    v1: Int, 
    v2: Int, 
    v3: Int
) : ClandestineClass(v1,v2,v3) {
    
    override fun method2() {
        println("Hey! Attempting to display values")
        println("$v1, $v2, $v3")
    }
    
}
```


    org.jetbrains.kotlinx.jupyter.exceptions.ReplCompilerException: at Cell In[58], line 9, column 29: Cannot access 'v3': it is invisible (private in a supertype) in 'SpecialClandestineClass1'


​    


The specializations of a class will have access to `public` and `protected` attributes. The code above will not compile because `SpecialClandestineClass1` doesn't have access to the private attribute `v3`. 

Ommiting `v3` inside the body of `method2`, the code now works:


```kotlin
open class SpecialClandestineClass2(
    v1: Int, 
    v2: Int, 
    v3: Int
) : ClandestineClass(v1,v2,v3) {
    
    override fun method2() {
        println("Hey! Attempting to display values")
        println("$v1, $v2")
    }
    
}
```

Note that although private attributes and methods of a general class cannot be accessed by its specializations, said attributes and methods are still inherited. For example, instances of `SpecialClandestineClass2` can still display the private attribute `v3` since it still exists. This works because the definition of `method1()` being used here is inherited from `ClandestineClass`.


```kotlin
val secret2 = SpecialClandestineClass2(1,2,3)
secret2.method1()
```

    Hey, these are my values
    1, 2, 3


Note that since private methods cannot be accessed by specializations, they cannot be have the `open` modifier as well.

Here is a summary of visibility modifiers and where they can be accessed

| visibility | accessed by specializations | accessed by clients (outside) |
| ---------- |  -------------------------- | ----------------------------- |
| public     |  yes                        | yes                           |
| protected  |  yes                        | no                            |
| private    |  no                         | no                            |


# Abstract Classes

Abstract classes are hybrids of interfaces and concrete classes. Abstract classes, unlike interfaces, can have attributes and contructors. Also, unlike concrete classes, it can be defined with abstract methods.

In the example below `AbsClass` has the modifier `abstract` making it an abstract class. The method `printSomethingA()` is an abstract function so all realizations of `AbsClass` must override it. While the method `printSomethingB()` is concrete, so it can be inherited or overridden.


```kotlin
abstract class AbsClass(val state: String) {
    
    abstract fun printSomethingA()

    open fun printSomethingB() {
        println("I'm inherited, you can also override me if you want")
    }

}

class ConcreteClass1(state: String) : AbsClass(state) {

    override fun printSomethingA() {
        println("I'm implemented by ConcreteClass1")
    }

}

class ConcreteClass2(state: String) : AbsClass(state) {

    override fun printSomethingA() {
        println("I'm implemented by ConcreteClass2")
    }

    override fun printSomethingB() {
        println("I'm overriden by ConcreteClass2")
    }
    
}
```


```kotlin
val c1 = ConcreteClass1("initial state")
val c2 = ConcreteClass2("initial state")
```


```kotlin
c1.printSomethingA()
c1.printSomethingB()
println()
c2.printSomethingA()
c2.printSomethingB()
```

    I'm implemented by ConcreteClass1
    I'm inherited, you can also override me if you want
    
    I'm implemented by ConcreteClass2
    I'm overriden by ConcreteClass2
