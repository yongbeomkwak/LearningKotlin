package Enum

enum class Color(
    val r: Int, val g: Int, val b: Int
) {
    RED(255,0,0),
    YELLOW(255,255,0),
    ORANGE(255, 100, 0),
    BLUE(0,0,255),
    GREEN(0,255,0),
    BLACK(255,255,255),
    SKYBLUE(0,10,255);


    fun printRGB() {
        println("${r},${g},${b}")
    }
}

fun getStringByColor(color: Color): String {
    return when(color) {
        Color.RED -> "apple"
        Color.ORANGE -> "orange"
        else -> "ABC"
    }

//    return when {
//        (color == Color.RED) -> "apple"
//        (color == Color.ORANGE) -> "orange"
//        else -> "ABC"
//    }

    // when으로 color가 들어왔는데 그 color가 RED면 apple, ORANGE orange를 리턴해라
    // color가 RED면 --> color == Color.RED --> TRUE OR FALSE
}

fun mixColor(color1: Color, color2: Color): Color {
    return when {
        (color1 == Color.RED && color2 == Color.YELLOW) -> Color.ORANGE
        else -> Color.BLACK
    }

    // when으로 color1 == RED && color2 == YELLOW면 , ORANGE 리턴해라
}

interface Fastable // 빠르게 다릴 수 있냐
class Car: Fastable
class AirPlane: Fastable

fun eval(e: Fastable) {

    when(e) {
        is Car -> println("e is Car")
        is AirPlane ->  println("e is Airplane")
    }

}