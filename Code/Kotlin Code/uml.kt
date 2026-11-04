@file:JvmName("UmlKt")
@file:Suppress("NO_REFLECTION_IN_CLASS_PATH")

package umlGenerate

import java.io.File
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty
import kotlin.reflect.KType

fun KType.shortType(): String {
    val classifier = this.classifier.toString().split(".").last()
    val shortArgs = this.arguments.map {
        it.type?.shortType() ?: ""
    }

    val argument = when {
        shortArgs.isEmpty() -> ""
        else -> shortArgs.joinToString(",", "<", ">")
    }

    return "$classifier$argument"
}

fun KParameter.asArgMember(): String =
    "${this.name}: ${this.type.shortType()}"

fun KCallable<*>.paramString(): String =
    this.parameters.filter {
        it.name != null
    }.joinToString(separator = ", ", transform = KParameter::asArgMember)

fun KCallable<*>.asUMLMember(): String {
    val vPrefix = when (this.visibility?.name) {
        "PRIVATE" -> "-"
        "PUBLIC" -> "+"
        "PROTECTED" -> "#"
        else -> ""
    }
    val suffix = when(this) {
        is KFunction -> "(${this.paramString()})"
        else -> ""
    }
    val shortReturnType = this.returnType.shortType()
    val returnType = if (shortReturnType == "Unit") "" else ": $shortReturnType"
    return "$vPrefix ${this.name}$suffix$returnType"
}

fun KClass<*>.umlMembers(): String {
    val declaredMembers = this.declaredProperties() + this.declaredMethods()
    val propertiesPlusMethods = declaredMembers.joinToString(
        separator = "\n    ",
        prefix = "    ",
        transform = KCallable<*>::asUMLMember
    )
    return listOf(this.constructors(), propertiesPlusMethods)
        .filter(String::isNotEmpty)
        .joinToString("\n")
}

fun KClass<*>.declaredMethods(): List<KFunction<*>> {
    val methodNames = this.java.declaredMethods.toList().map{ it.name }
    return this.members.filter {
        it.name in methodNames
    }.filterIsInstance<KFunction<*>>()
}

fun KClass<*>.declaredProperties(): List<KProperty<*>> {
    val fieldNames = this.java.declaredFields.toList().map{ it.name }
    return this.members.filter {
        it.name in fieldNames
    }.filterIsInstance<KProperty<*>>()
}

fun KClass<*>.constructors(): String =
    this.constructors.joinToString("\n") {
        "    ${this.shortType()}(${it.paramString()})"
    }

fun KClass<*>.shortType(): String =
    this.simpleName ?: "InvalidName"

fun KClass<*>.superTypedPrefix(): String =
    when {
        this.java.isInterface -> " ..|> ${this.shortType()}"
        else -> " --|> ${this.shortType()}"
    }

fun KClass<*>.relationships(): String =
    this.supertypes.filter {
        it.shortType() != "Any"
    }.fold("") { acc, it ->
        when (val superClass = it.classifier) {
            is KClass<*> -> "$acc\n${this.shortType()}${superClass.superTypedPrefix()}"
            else -> "$acc\n${this.shortType()} --|> UnknownClassName"
        }
    }.drop(1)

fun KClass<*>.header(): String =
    when {
        this.java.isInterface -> "interface ${this.shortType()}"
        this.isAbstract -> "abstract class ${this.shortType()}"
        else -> "class ${this.shortType()}"
    }

fun KClass<*>.classDiagram(): String =
    "${this.header()}\n{\n${this.umlMembers()}\n}\n${this.relationships()}\n\n"

fun KType.classifiersAndArgs(): Set<KClass<*>> {
    val argumentSet: MutableSet<KClass<*>> = mutableSetOf()
    if (this.classifier is KClass<*>)
        argumentSet.add(this.classifier as KClass<*>)
    this.arguments.mapNotNull { it.type }.forEach {
        argumentSet.addAll(it.classifiersAndArgs())
    }
    return argumentSet.toSet()
}

fun KClass<*>.dependencySet(): Set<KClass<*>> {
    val dependencies: MutableSet<KClass<*>> = mutableSetOf()

    for (method in this.declaredMethods()) {
        dependencies.addAll(method.parameters.map {
            it.type.classifier
        }.filterIsInstance<KClass<*>>())
        dependencies.addAll(listOf(method.returnType.classifier).filterIsInstance<KClass<*>>())
    }

    for (property in this.declaredProperties()) {
        dependencies.addAll(property.returnType.classifiersAndArgs())
    }

    return dependencies.toSet()
}

fun KClass<*>.dependencyRelationships(relevantClasses: Set<KClass<*>>) : String =
    this.dependencySet().intersect(relevantClasses).joinToString("\n") {
        "${this.shortType()} ..> ${it.shortType()}"
    }

fun uml(classes: List<KClass<*>>): String {
    val classSet = classes.toSet()
    val classDiagrams = classes.map(KClass<*>::classDiagram).fold("", String::plus)
    val dependencies = classes.map {
        it.dependencyRelationships(classSet.minusElement(it))
    }.filter(String::isNotEmpty).joinToString("\n")

    return "@startuml\n$classDiagrams$dependencies\n@enduml"
}

fun main(args: Array<String>) {
    val relevantClasses = args.dropLast(1).map {
        try {
            Class.forName(it).kotlin
        } catch (_: ClassNotFoundException) {
            println("Class not found: $it, skipping.")
            return@map null
        }
    }.filterNotNull()

    val umlFile = File(args.last())
    umlFile.writeText(uml(relevantClasses))

}