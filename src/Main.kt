import org.w3c.dom.css.Counter


fun String.a(): String? {
    println("a")
    return "aaa"
}

fun String.b(): String? {
    println("b")
    return null
}

fun String.c(): String? {
    println("c")
    return "c"
}

class Temp {
    private  lateinit var temp: String

    fun abc() {
        println(::temp.isInitialized) // false
        println(temp)  // ❌ lateinit property temp has not been initialized
    }
}
fun main() {
    lateinit 값을 나중에 초기화
            var val
}
