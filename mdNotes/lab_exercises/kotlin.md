# Lab Exercise 6 (Kotlin Introduction)

In the following exercise, you will edit the included library (`solutions.kt`) to implement the included functions:

- `doubledInt(x: Int): Int` - accepts some integer and returns the double of said integer
- `largest(x: Float, y: Float): Float` - accepts two floats and returns the largest value between the two
- `isVertical(a: Pair<Float, Float>, b: Pair<Float, Float>): Boolean` - accepts two pairs, each representing a point. Returns true if the points represent a vertical line. (note: if point `a` and point `b` are the same point then they don't form the same line)
- `primes(n: Int): List<Int>` - returns a list of the first `n` primes.
- `fibonacci(n: Int): List<Int>` - returns a list of the first `n` fibonacci numbers (starting from 0 and 1)
- `sortedIntegers(l: List<Int>): List<Int>` - returns a list of integers with the same elements but sorted from smallest to largest
- `sublists(l: List<Int>): List<List<Int>>` - accepts a list of integers and returns all the sublists of the list. Sublists are contiguous chunks of a list (including an empty list and the list itself). [1,2], [2], [], [2,3,4], and [1,2,3,4,5] are sublists of [1,2,3,4,5] but [3,5] and [1,2,3,4,6] are not.

