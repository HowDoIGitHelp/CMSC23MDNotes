package noPattern

class Context {
    var state: String = "InitialState" // AnotherState YetAnotherState

    fun stateDependentBehavior() {
        when (state) {
            "InitialState" -> {
                state = "AnotherState"
            }
            "AnotherState" -> {
                state = "InitialState"
            }
            "YetAnotherState" -> {
                state = "YetAnotherState"
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
            "YetAnotherState" -> {
                println("Context currently not on initial state")
            }
            else -> println("invalid state")
        }
    }
}

fun clientFunction() {
    val c = Context()

    // c.stateDependentBehavior()
    c.state = "YetAnotherState"
    c.anotherStateDependentBehavior()
}

fun main() {
    clientFunction()
}
