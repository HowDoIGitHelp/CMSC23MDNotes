package badRefactor

interface AbstractDependency {
    fun dependencyMethod()
}

interface RequiredInterface {
    fun requiredMethod()
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
    //clientMethod2(dependency)
}