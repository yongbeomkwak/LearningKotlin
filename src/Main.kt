import org.w3c.dom.css.Counter
import java.util.HashMap
import java.util.LinkedList
import java.util.Objects

data class Point(var x: Int, var y: Int) {
    operator fun get(index: Int): Int {
        return when(index) {
            0 -> x
            1 -> y
            else -> throw IndexOutOfBoundsException("Invalid ${index}")
        }
    }

    operator fun set(index: Int, value: Int) {
        return when(index) {
            0 -> x = value
            1 -> y = value
            else -> throw IndexOutOfBoundsException("Invalid ${index}")
        }
    }
}

fun main() {
    var aPoint = Point(100, 200)

    aPoint[0] = 200
    aPoint[1] = 100

    print("${aPoint[0]} ${aPoint[1]}") // 200, 100
}
