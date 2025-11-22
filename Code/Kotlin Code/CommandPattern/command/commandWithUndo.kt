package command

class Receiver {
    var contents: String = "some string"

    fun behavior1(param: String) {
        println("performing behavior 1 with $param")
        contents += param
    }

    fun behavior2(param: String, otherParam: String) {
        println("performing behavior 2 with $param and $otherParam")
        contents += param + otherParam
    }

    fun restore(previousVersion: Receiver) {
        this.contents = previousVersion.contents
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
        receiver.restore(backup)
    }
}

class RealCommand2(val param: String, val otherParam: String, val receiver: Receiver) : Command {
    val backup = receiver.copy()

    override fun execute() {
        receiver.behavior2(param, otherParam)
    }

    override fun undo() {
        receiver.restore(backup)
    }
}

class Invoker {
    val commandHistory: MutableList<Command> = mutableListOf()
    val receiver = Receiver()

    fun invokeBehavior1(param: String) {
        val command = RealCommand1(param, receiver)
        command.execute()
        commandHistory.add(command)
    }

    fun invokeBehavior2(param: String, otherParam: String) {
        val command = RealCommand2(param, otherParam, receiver)
        command.execute()
        commandHistory.add(command)
    }

    fun undoPreviousCommand() {
        val lastCommand = commandHistory.last()
        lastCommand.undo()
        commandHistory.removeLast()
    }
}

fun main() {
    val invoker = Invoker()
    println("receiver state: ${invoker.receiver.contents}")
    invoker.invokeBehavior1("foo")
    println("receiver state: ${invoker.receiver.contents}")
    invoker.invokeBehavior2("foo", "bar")
    println("receiver state: ${invoker.receiver.contents}")
    invoker.undoPreviousCommand()
    println("undoing..")
    println("receiver state: ${invoker.receiver.contents}")
    invoker.undoPreviousCommand()
    println("receiver state: ${invoker.receiver.contents}")
}