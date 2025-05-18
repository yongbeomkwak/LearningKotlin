import org.w3c.dom.css.Counter

class Bus(speed: Int) {
    val speedLimit: Int = 50

    private var speed: Int

    init {
        this.speed = speed
    }

    fun getSpped(): Int {
        return speed
    }

    fun updateSpped(to: Int) {
        if (to > speedLimit) {
            return
        }
        this.speed = to
    }
}

fun printHello() = println("Hello")

fun withOut(): String {
    var numbers = mutableListOf<Int>()
    for (letter in 1..10) {
        numbers.add(letter)
    }

    println(numbers)
    return numbers.toString()
}

fun  with(): String {
    var numbers = mutableListOf<Int>()

    with(numbers) {
        for (letter in 1..10) {
            add(letter)
        }
        return this.toString()
    }
}

fun _apply(): List<Int> {
    var numbers = mutableListOf<Int>()
    val result = numbers.apply {
        for (letter in 1..10) {
            add(letter)
        }
    }

    println(result) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    println(numbers) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    return result
}

fun main() {
    val result = _apply()
    println(result)
}
