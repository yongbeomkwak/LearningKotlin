class OuterClass1 {
    var outerProperty: Int = 3
    class NestedClass {
        fun printOuter() {
            println("바깥쪽에 대한 참조가 없음")
        }
    }
}

class OuterClass2 {
    var outerProperty: Int = 3
    inner class InnerClass {
        fun printOuter() {
         println(this@OuterClass2.outerProperty)
        }
    }
}

interface Expr1
class Num1: Expr1
class Sum1: Expr1

fun eval1(e: Expr1): Int {
    return when(e) {
        is Num1 ->  1
        is Sum1 ->  2
        else -> 0
    }
}

sealed class Expr2 {
    class Num2: Expr2()
    class Sum2: Expr2()
}

fun eval2(e: Expr2): Int {
    return when(e) {
        is Expr2.Num2 ->  1
        is Expr2.Sum2 ->  2
    }
}

class User1(val name: String)

class User2 (name: String) {
    private val _name: String

    init {
        this._name = name
    }
}

class PrivateUser private constructor(val name: String) {

}

class SubUser(name: String, age: Int) {
    private val _name: String
    private val _age: Int
    init {
        println("주 생성자 호출")
        this._name = name
        this._age = age
    }

    constructor(name: String): this(name, 10)  {
        println("부 생성자 호출")
    }

    constructor(age: Int): this("기본", age) {
        println("부 생성자 호출")
    }
}

open class SuperUser(val name: String) {
    init {
        println("Super User 주 생성자 호출")
    }
}

class User3(name: String, age: Int): SuperUser(name) {
    val _age: Int
    init {
        println("User 3 주 생성자 호출")
        this._age = age
    }

    constructor(age: Int): this("고정", 10) {
        println("User 3 부 생성자 호출")
    }

}

open  class SuperUser2(name: String) {
//    constructor(name: String)
    constructor(name: String, age: Int): this(name)
}

class User4: SuperUser2 {
    constructor(name: String): super(name) {}
    constructor(name: String, age: Int): super(name,age) {}
}



fun main() {
    // ✅ NestedClass 인스턴스 생성 (외부 클래스 필요 없음)
    val nested = OuterClass1.NestedClass()
    nested.printOuter() // 출력: "바깥쪽에 대한 참조가 없음"

    // ✅ InnerClass 인스턴스 생성 (외부 클래스의 인스턴스 필요)
    val outer = OuterClass2()
    val inner = outer.InnerClass()
    inner.printOuter() // 출력: "3"
    println("=========================")
    val n1 = Num1()
    val s1 = Sum1()
    println(eval1(n1))
    println(eval1(s1))

    val n2 = Expr2.Num2()
    val s2 = Expr2.Sum2()
    println("=========================")
    println(eval2(n2))
    println(eval2(s2))

    val user1 = User1("헬로")
    println(user1.name)

    val user2 = User2("헬로2")
    println("_name2") // 접근 불가

    val subUser = SubUser("NN")

    val user3 = User3(20)
}

