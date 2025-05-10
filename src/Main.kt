import org.w3c.dom.css.Counter

fun main() {
    var number1: Int = 0
    val incr = { number1 ++}

    incr()
    class Ref<T>(var value: T)

    val counter = Ref(0)
    val incr = { counter.value ++}
    incr()

    println(number1)
}
