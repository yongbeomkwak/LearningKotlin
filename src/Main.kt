import java.net.URL
import kotlin.random.Random
fun <T> joinToString(collection: Collection<T>, separator: String, prefix: String, postfix: String): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString();
}

infix fun Int.to(other: Any) = Pair(this, other)

fun print(vararg values: Int) {
    values.forEach {
        println(it)
    }
}

fun main() {
    print(1,2,3,4,5)
    val (number, element) = 1 to "Name"
    println("${number} ${element}")
}

