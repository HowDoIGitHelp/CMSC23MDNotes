package strategy

fun clientFunction(strategy: Strategy) {
    val parameter = "some string"
    strategy.execute(parameter)
}

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

fun main() {
    clientFunction(Strategy1())
}
