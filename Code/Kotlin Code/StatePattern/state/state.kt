package state

class Context {
    private var currentState: State = InitialState(this)

    fun changeState(newState: State) {
        currentState = newState
    }

    fun behavior() {
        currentState.stateDependentBehavior()
    }

    fun anotherBehavior() {
        currentState.anotherStateDependentBehavior()
    }
}

interface State {
    fun stateDependentBehavior()

    fun anotherStateDependentBehavior()
}

class InitialState(
    private val owner: Context,
) : State {
    override fun stateDependentBehavior() {
        owner.changeState(AnotherState(owner))
    }

    override fun anotherStateDependentBehavior() {
        println("Context currently on initial state")
    }
}

class AnotherState(
    private val owner: Context,
) : State {
    override fun stateDependentBehavior() {
        owner.changeState(InitialState(owner))
    }

    override fun anotherStateDependentBehavior() {
        println("Context currently not on initial state")
    }
}

fun clientMethod() {
    val c = Context()

    c.behavior()
    c.anotherBehavior()
}

fun main() {
    clientMethod()
}
