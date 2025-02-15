abstract class Ac {
    abstract fun AcFunction()
}

class C1 {

}

abstract class C2 {
    abstract fun hello()
}

open class C3: C2() {
    override fun hello() {
        TODO("Not yet implemented")
    }
    open fun hello2(){}
}


//class Base: Ac() {
//    override fun AcFunction() {
//        TODO("Not yet implemented")
//    }
//}

//class Base: C1() {
//
//}

class  Base: C3(){
    override fun hello() {
        super.hello()
    }
}

fun main() {


}

