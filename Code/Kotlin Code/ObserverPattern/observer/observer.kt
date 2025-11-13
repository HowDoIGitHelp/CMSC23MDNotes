package observer

interface Observer {
    fun updateSubject(newSubject: String)
}

class RealObserver1 : Observer {
    var subjectCopy = ""

    override fun updateSubject(newSubject: String) {
        subjectCopy = newSubject
    }
}

class RealObserver2 : Observer {
    override fun updateSubject(newSubject: String) {
        println("subject has been updated new subject is: $newSubject")
    }
}

class Publisher {
    private var subject = ""
    private val subscribers: MutableList<Observer> = mutableListOf()

    fun updateSubject(newSubject: String) {
        subject = newSubject
        notifyObservers()
    }

    fun subscribe(newObserver: Observer) {
        subscribers.add(newObserver)
        newObserver.updateSubject(subject)
    }

    fun unsubscribe(exObserver: Observer) {
        subscribers.remove(exObserver)
    }

    fun notifyObservers() {
        for (subscriber in subscribers) {
            subscriber.updateSubject(subject)
        }
    }
}

fun main() {
    val publisher = Publisher()
    val observer1 = RealObserver1()
    val observer2 = RealObserver2()

    publisher.subscribe(observer1)
    publisher.subscribe(observer2)

    publisher.updateSubject("updated subject")
    println(observer1.subjectCopy)
}