interface State {
    fun stateDependentBehavior()
    fun anotherStateDependentBehavior()
}

class InitialState(private var owner: Context) : State {
    override fun stateDependentBehavior() {
        owner.currentState = AnotherState(owner)
    }

    override fun anotherStateDependentBehavior() {
        println("Context currently on initial state")
    }
}

class AnotherState(private var owner: Context) : State {
    override fun stateDependentBehavior() {
        owner.currentState = InitialState(owner)
    }

    override fun anotherStateDependentBehavior() {
        println("Context currently not on initial state")
    }
}

class Context {
    var currentState: State = InitialState(this)

    fun behavior() {
        currentState.stateDependentBehavior()
    }

    fun anotherBehavior() {
        currentState.anotherStateDependentBehavior()
    }
}

fun clientFunction() {
    val c = Context()

    c.behavior()
    println(c.currentState)
    c.anotherBehavior()
}

fun main() {
    clientFunction()
}
