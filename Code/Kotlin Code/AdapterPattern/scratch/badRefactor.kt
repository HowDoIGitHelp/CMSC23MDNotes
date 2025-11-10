interface AbstractDependency {
    fun dependencyMethod()
}

interface RequiredInterface {
    fun requiredMethod()
}

class Adapter(private val embeddedDependency: RealDependency): RequiredInterface {
    override fun requiredMethod() {
        embeddedDependency.dependencyMethod()
    }
}

class RealDependency: AbstractDependency {
    override fun dependencyMethod() {
        println("performing dependency method")
    }
}

fun clientMethod(dependency: RealDependency) {
    dependency.dependencyMethod()
}

fun clientMethod2(requirement: RequiredInterface) {
    requirement.requiredMethod()
}

fun main() {
    val dependency = RealDependency()
    clientMethod(dependency)
    clientMethod2(Adapter(dependency))
}