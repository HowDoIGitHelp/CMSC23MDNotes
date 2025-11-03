package noPattern

fun clientFuntion(strategy: Int) {
    val parameter = "some string"
    when (strategy) {
        1 -> strategy1(parameter)
        2 -> strategy2(parameter)
        else -> println("invalid stretegy chosen")
    }
}

fun strategy1(param: String) {
    println("performing strategy1 with parameters: $param")
}

fun strategy2(param: String) {
    println("performing strategy2 with parameters: $param")
}

fun main() {
    clientFuntion(1)
}
