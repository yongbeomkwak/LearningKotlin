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

class AI {

}

val AI.condition
    get() = "기분이 좋다."



fun main() {
    val ai = AI()
    println(ai.condition)
}

