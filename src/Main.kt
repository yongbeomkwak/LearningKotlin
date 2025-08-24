//open class Animal {
//    fun feed(){}
//}
//
//class Herd<out T: Animal> {
//    val size: Int get() { return 10 }
//
//    operator  fun get(i: Int): T {}
//}
//
//fun feedAll(animals: Herd<Animal>) {
//    for (i in 0 until animals.size) {
//        animals[i].feed()
//    }
//}
//
//class  Cat: Animal() {
//    fun cleanLitter() {}
//}
//
//fun takeCareOfCats(cats: Herd<Cat>) {
//    for (i in 0 until cats.size) {
//        cats[i].cleanLitter()
//    }
//
//    feedAll(cats)
//}

class  ABC {}

fun isABC(value: Any): Boolean {
    return value is ABC
}

fun  isInt(value: Any): Boolean {
    return value is Int
}

inline fun  <reified T> isT(value: Any): Boolean {
    return value is T
}

// 너 T야? T->
inline fun <reified T> isA(value : Any) = value is T

fun isA(value: Any) = value is String

fun main(args : Array<String>){
    println(isA<String>("abc")) 		//true
    println(isA<String>(123))			//false
}

fun main() {

//    abc()
//    abc()
//    abc()
//    abc()
//    abc()
//    abc()
//    abc()
//    abc()
//    abc()
//    abc()

    println("나 호출됨")
    println("실행됨")
    println("실행됨")
    println("잘못된 코드")
    println("나 호출됨")
    println("실행됨")
    println("실행됨")
    println("잘못된 코드")
    println("나 호출됨")
    println("실행됨")
    println("실행됨")
    println("잘못된 코드")

}

