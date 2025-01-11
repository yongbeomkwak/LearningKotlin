
class Temp {
    var feild1: Int = 0  // 3가지 튀어나온다, (private) 필드, public getter와 setter가 튀어나온다.

    public var fieldAlt: Int
        get() {
            return
        }
        set(value) {
            feild1 = value * 2
        }
}

fun main() {
    // 설계가 쉬움, 사이드 이팩트가 생각보다 관리가 안된다.
    var temp = Temp()
    println(temp.fieldAlt)
    temp.fieldAlt = 100
    println("${temp.fieldAlt} ${temp.feild1}")
}
