interface Consumer<in T> {
    fun consume(item: T)  // ✅ 가능 (넣는 건 안전)
    // fun produce(): T ❌ 불가능 (꺼내는 건 위험)
}

fun main() {
    val anyConsumer: Consumer<Any> = object : Consumer<Any> {
        override fun consume(item: Any) = println(item)
    }
    val strConsumer: Consumer<String> = anyConsumer

    println(strConsumer.consume("123"))
}