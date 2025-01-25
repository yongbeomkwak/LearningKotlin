fun main() {
    val a: Int = 5

    if (a in 0..9) {
        println("${a}는 0부터 9까지 범위 안에 있습니다.")
    } else {
        println("${a}는 0부터 9까지 범위 안에 없습니다.")
    }

    when(a) {
        in 0..9 -> println("${a}는 0부터 9까지 범위 안에 있습니다.")
        else ->  println("${a}는 0부터 9까지 범위 안에 없습니다.")
    }

}
