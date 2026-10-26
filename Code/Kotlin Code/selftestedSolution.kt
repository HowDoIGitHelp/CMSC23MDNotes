fun doubledInt(x: Int): Int {
    return 0
}


fun largest(x: Float, y: Float): Float {
    return 0f
}

fun isVertical(a: Pair<Float, Float>, b: Pair<Float, Float>): Boolean {
    return false
}

fun primes(n: Int): List<Int> {
    return listOf(2)
}

fun fibonacci(n: Int): List<Int> {
    return listOf(0)
}

fun sortedIntegers(l: List<Int>): List<Int> {
    return l
}

fun sublists(l: List<Int>): List<List<Int>> {
    return listOf(l)
}

fun testDoubledInt() {
    for (case in -10..10) {
        var actual = doubledInt(case)
        var expected = case + case

        check(expected == actual) {
            "calling doubledInt with argument $case, expected $expected but got $actual"
        }
    }
    println("doubledInt test: success")
}

fun testLargest() {
    for (arg1 in -10..10) {
        for (arg2 in -10..10) {
            var first = arg1.toFloat()
            var second = arg2.toFloat()

            var actual = largest(first, second)
            var expected = if (first > second) first else second

            check(expected == actual) {
                "calling largest with arguments $first and $second, expected $expected but got $actual"
            }
        }
    }
    println("largest test: success")
}

fun testIsVertical() {
    val arg1List: List<Pair<Float, Float>> = listOf(Pair(10.1f, 3.4f), Pair(10.3f, 4.5f), Pair(0.2f, 2.1f), Pair(2.2f, 1.0f))
    val arg2List: List<Pair<Float, Float>> = listOf(Pair(10.1f, -3.2f), Pair(0.0f, 2.1f), Pair(1.3f, 2.1f), Pair(2.2f, 1.0f))

    for (arg1 in arg1List) {
        for (arg2 in arg2List) {
            var actual = isVertical(arg1, arg2)
            var expected = arg1.first == arg2.first && arg1.second != arg2.second

            check(expected == actual) {
                "calling isVertical with arguments $arg1 and $arg2, expected $expected but got $actual"
            }
        }
    }
    println("isVertical test: success")
}

fun testPrimes() {
    val primeList = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541)
    
    for (case in 1..50 step 10) {
        var actual = primes(case)
        var expected = primeList.slice(0..(case - 1))

        check(expected == actual) {
            "calling primes with argument $case, expected $expected but got $actual"
        }
    } 
    println("primes test: success")
}

fun testFibonacci() {
    val fibonacciSequence: List<Int> = listOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229)

    for (case in 0..20 step 2) {
        var actual = fibonacci(case).toList()
        var expected = fibonacciSequence.slice(0..(case - 1)).toList()

        check(expected == actual) {
            "calling fibonacci with argument $case, expected $expected but got $actual"
        }
    }
    println("fibonacci test: success")
}

fun hasSameElements(u: List<Int>, v: List<Int>): Boolean {
    val l = u.toMutableList()
    val m = v.toMutableList()

    if (l.size != m.size)
        return false

    else {
        while (l.size > 0) {
            var removedElem = l.removeAt(0)
            if (removedElem in m)
                m.remove(removedElem)
            else
                return false
        }
        return true
    }
}

fun isSorted(l: List<Int>): Boolean {
    for (i in 0..(l.size-2)) {
        if (l[i] > l[i+1])
            return false
    }
    return true
}

fun testSortedIntegers() {
    val cases: List<List<Int>> = listOf(
        listOf(0,2,-1,3,1,0,4,3),
        listOf(1,2,3,4,5,6),
        listOf(),
        listOf(1,1,1,1,1,1),
        listOf(-1,-2,-3,-4,-5,-6),
        listOf(-1)
    ) 

    for (case in cases) {
        var sortedCase = sortedIntegers(case)

        check(hasSameElements(case, sortedCase)) {
            "calling sortedIntegers with argument $case, got $sortedCase. Solution incomplete or excessive"
        }
        check(isSorted(sortedCase)) {
            "calling sortedIntegers with argument $case, got $sortedCase. Solution is not sorted"
        }
    }

    println("sortedIntegers test: success")
}

fun indicesOf(n: Int, l: List<Int>): List<Int> {
    var locations: MutableList<Int> = mutableListOf()
    l.forEachIndexed { index, elem ->
        if (elem == n)
            locations.add(index)
    }
    return locations.toList()
}

fun isSublist(subl: List<Int>, l: List<Int>): Boolean {
    for (i in 0..(l.size-subl.size)) {
        when (l.slice(i..(i+subl.size-1))) {
            subl -> return true
            else -> { }
        }
    }
    return false
}

fun isUniqueInt(a: Any, l: List<Any>) : Boolean {
    return (l.filter { it == a }).size == 1
}

fun hasUniqueElements(l: List<Any>) : Boolean {
    return l.all {
        isUniqueInt(it, l)
    }
}

fun testSublists() {
    val cases: List<List<Int>> = listOf(
        listOf(),
        listOf(1,2,3,4),
        listOf(0),
        listOf(0,1,2,3,4,5)
    )

    cases.forEach {
        var actual = sublists(it)
        check(hasUniqueElements(actual)) {
            "test failed, calling sublists with $it, got: $actual. solution must not repeat sublists"
        }

        check(actual.size == it.size*(it.size+1)/2 + 1) {
            "test failed, calling sublists with $it, got: $actual. solution is either incomplete or excessive"
        }

        var allSublists = actual.all { sublist ->
            isSublist(sublist, it)
        }

        check(allSublists) {
            "test failed, calling sublists with $it, got $actual. some elements in the solution are not sublists"
        }
    }

    println("sublists test: success")
}


fun main(arg: Array<String>) {
    val testList = listOf(
        ::testDoubledInt, 
        ::testLargest, 
        ::testIsVertical, 
        ::testPrimes, 
        ::testFibonacci,
        ::testSortedIntegers,
        ::testSublists
    )
    try {
        for (test in testList)
          test()
    } 
    catch (e: IllegalStateException) {
        println(e.message)
    }
}


