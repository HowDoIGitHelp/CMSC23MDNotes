interface Strategy {
    fun execute(param: String)
}

class Strategy1 : Strategy {
    override fun execute(param: String) {
        println("performing strategy1 with parameters: $param")
    }
}

class Strategy2 : Strategy {
    override fun execute(param: String) {
        println("performing strategy2 with parameters: $param")
    }
}

class Strategy3 : Strategy {
    override fun execute(param: String) {
        println("im a new strategy with: $param")
    }
}

fun clientFunction(strategy: Strategy) {
    val parameter = "some string"
    strategy.execute(parameter)
}

fun main() {
    clientFunction(Strategy3())
}