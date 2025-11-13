class Receiver {
    var contents: String = ""

    fun behavior1(param: String) {
        println("performing behavior 1 with $param")
        contents = param
    }

    fun behavior2(param: String, otherParam: String) {
        println("performing behavior 2 with $param and $otherParam")
        contents = param + otherParam
    }
}

interface Command {
    fun execute()
}

class RealCommand1(val param: String, val receiver: Receiver) : Command {
    override fun execute() {
        receiver.behavior1(param)
    }
}

class RealCommand2(val param: String, val otherParam: String, val receiver: Receiver) : Command {
    override fun execute() {
        receiver.behavior2(param, otherParam)
    }
}

class Invoker {
    fun invokeBehavior1(param: String, receiver: Receiver) {
        val command = RealCommand1(param, receiver)
        command.execute()
    }

    fun invokeBehavior2(param: String, otherParam: String, receiver: Receiver) {
        val command = RealCommand2(param, otherParam, receiver)
        command.execute()
    }
}
