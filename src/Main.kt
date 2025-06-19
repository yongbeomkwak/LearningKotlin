import org.w3c.dom.css.Counter
import java.util.LinkedList
import java.util.Objects


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
    private  lateinit var temp: String // 컴파일러야, 지금은 아무 값도 없지만, 나중에 사용할 때 무조건 값이 있으니깐, 얘는 널체크할 필요없어



    fun abc() {
        val prop = this::temp
        prop

        println(temp)  // ❌ lateinit property temp has not been initialized
    }
}

class DummyInt(a: Int) {
    companion object {}
}


open class A {}

class B: A() {}

fun main() {

}
