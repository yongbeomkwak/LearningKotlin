import org.w3c.dom.css.Counter

class Bus(speed: Int) {
    val speedLimit: Int = 50

    private var speed: Int

    init {
        this.speed = speed
    }

    fun getSpped(): Int {
        return speed
    }

    fun updateSpped(to: Int) {
        if (to > speedLimit) {
            return
        }
        this.speed = to
    }
}

fun printHello() = println("Hello")

fun main() {
    val original: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)
    val new = original
        .filter { it % 2 == 0 } // 중간 임시 컬렉션 생성
        .map{ it.toDouble() } // 중간 임시 컬렉션 생성 ..
    println(new)
}
