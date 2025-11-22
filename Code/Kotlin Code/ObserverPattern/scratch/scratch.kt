package scratch

interface Observer {
    fun update(newSubject: String)
}

class Publisher(private var subject: String) {
    val subscribers: MutableList<Observer> = mutableListOf()

    fun notifyObservers() {
        for (sub in subscribers) {
            sub.update(subject)
        }
    }

    fun updateSubject(newSubject: String) {
        subject = newSubject
        notifyObservers()
    }

    fun subscribe(newSubscriber: Observer) {
        subscribers.add(newSubscriber)
        newSubscriber.update(subject)
    }

    fun unsubscribe(exSubscriber: Observer) {
        subscribers.remove(exSubscriber)
    }
}

class RealObserver1 : Observer {
    var subjectCopy = ""
    override fun update(newSubject: String) {
        subjectCopy = newSubject
    }
}

class RealObserver2 : Observer {
    override fun update(newSubject: String) {
        println("subject has been changed to: $newSubject")
    }
}

class Client(var dataCopy: String) {
    fun hasChanged(interestingData: String): Boolean {
        if (dataCopy != interestingData) {
            dataCopy = interestingData
            return true
        } else {
            return false
        }
    }

    fun announceChanges() {
        println("subject has changed to $dataCopy")
    }
}
fun main() {
    val publisher = Publisher("some string")
    val observer1 = RealObserver1()
    val observer2 = RealObserver2()

    publisher.subscribe(observer1)
    publisher.subscribe(observer2)

    publisher.updateSubject("updated subject")
    println(observer1.subjectCopy)

    publisher.unsubscribe(observer1)
    publisher.updateSubject("newer subject")

    println(observer1.subjectCopy)

}
