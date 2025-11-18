package noPattern

interface Template {
    fun method()
}

class Specialization1 : Template {
    override fun method() {
        println("performing step 1 as specialization 1")
        println("performing step 2")
        println("performing step 3")
        println("performing step 4 as specialization 1")
    }
}

class Specialization2 : Template{
    override fun method() {
        println("performing step 1 as specialization 2")
        println("performing step 2")
        println("performing step 3")
        println("performing step 4 as specialization 2")
    }
}

fun clientFunction() {
    val o: Template = Specialization1()
    o.method()
}

fun main() {
    clientFunction()
}