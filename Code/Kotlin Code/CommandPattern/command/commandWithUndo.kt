package command

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

    fun restore() {
        println("restoring")
    }

    fun copy(): Receiver {
        val copy = Receiver()
        copy.contents = contents
        return copy
    }
}

interface Command {
    fun execute()
    fun undo()
}

class RealCommand1(val param: String, val receiver: Receiver) : Command {
    val backup = receiver.copy()
    override fun execute() {
        receiver.behavior1(param)
    }

    override fun undo() {
        receiver.contents = backup.contents
    }
}

class RealCommand2(val param: String, val otherParam: String, val receiver: Receiver) : Command {
    val backup = receiver.copy()
    override fun execute() {
        receiver.behavior2(param, otherParam)
    }

    override fun undo() {
        receiver.contents = backup.contents
    }
}

class Invoker {
    val commandHistory: MutableList<Command> = mutableListOf()

    fun invokeBehavior1(param: String, receiver: Receiver) {
        val command = RealCommand1(param, receiver)
        command.execute()
        commandHistory.add(command)
    }

    fun invokeBehavior2(param: String, otherParam: String, receiver: Receiver) {
        val command = RealCommand2(param, otherParam, receiver)
        command.execute()
    }

    fun undoPreviousCommand() {
        commandHistory.last().undo()
        commandHistory.removeLast()
    }
}