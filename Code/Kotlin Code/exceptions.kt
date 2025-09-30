import kotlin.math.floor

class DivisionByZeroError(message: String) : Exception(message)


fun quotientUnsafe(a: Int, b:Int): Int {
    return a/b
}

fun quotient(a: Float, b: Float): Float {
    when (b) {
        0f -> throw DivisionByZeroError("Division By Zero: $a/$b")
        else -> return a/b
    }
}

fun mixedFraction(wholePart: Int, numerator: Int, denominator: Int): Float {
    return wholePart + quotient(numerator.toFloat(), denominator.toFloat())
}

fun quotientString(a: Float, b: Float): String {
    try {
        val quotient = quotient(a,b)
        val wholePart = floor(quotient(a,b))
        return "whole part: $wholePart, decimal part: ${quotient - wholePart}" 
    } catch (ex: DivisionByZeroError) {
        return "undefined number"
    }
}

fun quotientList(l: List<Float>, m: List<Float>): List<Float> {
    val r: MutableList<Float> = mutableListOf()
    for (i in 0..(l.size-1)) {
        try {
            r.add(quotient(l[i], m[i]))
        } catch (ex: DivisionByZeroError) {
            r.add(Float.POSITIVE_INFINITY)
        } catch (ex: IndexOutOfBoundsException) {
            //do not append anything to the list
        }
    }
    return r.toList()
}

fun main() {
    println(quotientList(listOf(1f,2f,3f,4f,5f,6f), listOf(3f,0f,2f,2f,1f)))
}
