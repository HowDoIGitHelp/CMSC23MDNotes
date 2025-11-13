
fun clientFuntion(strategy: (String) -> Unit) {
    val parameter = "some string"
    strategy(parameter)
}

fun strategy1(param: String) {
    println("performing strategy1 with parameters: $param")
}

fun strategy2(param: String) {
    println("performing strategy2 with parameters: $param")
}

fun main() {
    clientFuntion(::strategy1)
}
