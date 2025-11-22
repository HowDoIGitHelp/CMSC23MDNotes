package noPattern

interface Command {
    fun execute()
}

class RealCommand1(
    val param: String,
    val receiver: Receiver
) : Command {
    override fun execute() {
        receiver.behavior1(param)
    }
}

class RealCommand2(
    val param1: String,
    val param2: String,
    val receiver: Receiver
) : Command {
    override fun execute() {
        receiver.behavior2(param1, param2)
    }
}

class Receiver {
    val contents = "some string"
    fun behavior1(param: String) {
        println("behavior 1 performed with param: $param")
    }

    fun behavior2(param1: String, param2: String) {
        println("behavior 2 performed with params: $param1, $param2")
    }
}

class Invoker {
    val receiver = Receiver()
    fun invokeBehavior1(param: String) {
        val command: Command = RealCommand1(param, receiver)
        command.execute()
    }

    fun invokeBehavior2(param1: String, param2: String) {
        val command: Command = RealCommand2(param1, param2, receiver)
        command.execute()
    }
}

fun main() {
    val invoker = Invoker()
    invoker.invokeBehavior1("foo")
    invoker.invokeBehavior2("foo", "bar")

    val receiver = Receiver()
    receiver.behavior1("foo")
}