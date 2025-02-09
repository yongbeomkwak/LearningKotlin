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

fun main() {
    val (number, element) = 1 to "Name"
    println("${number} ${element}")
}

