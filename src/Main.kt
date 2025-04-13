object TempObject {
    val number: Int

    init {
        number = 11
    }
}

// 선언, 정의
// 생성, 구현
class User private constructor(private val nickname: String){ // 주 생성자를 private으로 생성
    companion object { // 동반 객체 선언
        fun newSubscribingUser(email: String) = User(email.substringBefore('@'))
        fun newFacebookUser(accountId: Int) = User("${accountId}")
        fun createUser(): User {
            return User("HH")
        }

        fun printUserName(user: User) {
            println(user.nickname) // ✅ private 멤버에 접근 가능!
        }
    }
}

interface ClickListener {
    fun onClick()
}

sealed class Expr2 {
    class Num2: Expr2()
    class Sum2: Expr2()
    class Kum2: Expr2()
}

fun eval2(e: Expr2): Int {
    return when(e) {
        is Expr2.Num2 ->  1
        is Expr2.Sum2 ->  2
        is Expr2.Kum2 -> 3
    }
}

// 선언과 생성 = 인스턴스가 1 개뿐 -> 싱글톤 패턴에 쓰기 좋다
object Toy {
    var amount: Int = 0
}

// 선언
class CA

fun main() {
    var ca1 = CA() // 생성
    var ca2 = CA() // 생성 , 1 2

    var a = Toy.amount
}


