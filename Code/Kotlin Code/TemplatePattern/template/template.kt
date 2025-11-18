package template

abstract class Template {
    fun templateMethod() {
        step1()
        step2()
        step3()
        step4()
    }

    protected abstract fun step1()
    protected abstract fun step4()

    protected open fun step2() {
        println("performing step 2")
    }

    protected open fun step3() {
        println("performing step 3")
    }
}

class Specialization1 : Template() {
    override fun step1() {
        println("performing step 1 as specialization 1")
    }

    override fun step4() {
        println("performing step 4 as specialization 1")
    }
}

class Specialization2 : Template() {
    override fun step1() {
        println("performing step 1 as specialization 2")
    }

    override fun step4() {
        println("performing step 4 as specialization 2")
    }
}

fun clientFunction() {
    val o: Template = Specialization2()
    o.templateMethod()
}

fun main() {
    clientFunction()
}