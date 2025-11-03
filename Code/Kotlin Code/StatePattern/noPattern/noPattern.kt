package noPattern

class Context {
    var state: String = "InitialState"

    fun stateDependentBehavior() {
        when (state) {
            "InitialState" -> {
                state = "AnotherState"
            }
            "AnotherState" -> {
                state = "InitialState"
            }
            else -> println("invalid state")
        }
    }

    fun anotherStateDependentBehavior() {
        println("another state dependent behavior, currentState: $state")

        when (state) {
            "InitialState" -> {
                println("Context currently on initial state")
            }
            "AnotherState" -> {
                println("Context currently not on initial state")
            }
            else -> println("invalid state")
        }
    }
}

fun clientFunction() {
    val c = Context()

    c.stateDependentBehavior()
    c.anotherStateDependentBehavior()
}

fun main() {
    clientFunction()
}
