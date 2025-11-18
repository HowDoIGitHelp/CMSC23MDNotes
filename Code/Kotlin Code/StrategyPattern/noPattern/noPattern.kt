package noPattern

fun clientFunction(strategy: Int) {
    val parameter = "some string"
    when (strategy) {
        1 -> strategy1(parameter)
        2 -> strategy2(parameter)
        3 -> strategy3(parameter)
        else -> println("invalid stretegy chosen")
    }
}

fun strategy1(param: String) {
    println("performing strategy1 with parameters: $param")
}

fun strategy2(param: String) {
    println("performing strategy2 with parameters: $param")
}

fun strategy3(param: String) {
    println("i am a new strategy")
}

fun main() {
    clientFunction(1)
}
