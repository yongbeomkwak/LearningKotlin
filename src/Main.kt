
class Temp(val name: String, val age: Int) {
    override fun toString(): String {
        return "${name} ${age}"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Temp) {
            return false
        }
        return name == other.name && age == other.age
    }

//    override fun hashCode(): Int {
//        return name.hashCode() * 31 + age
//    }
}

data class Client(val name: String, val age: Int)

class CountingSet<T>(
    val innerSet: MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innerSet { // MutablleCollection의 구현을 innerSet 에게 위임한다.
    var objectsAdded = 0
    override fun add(element: T): Boolean { // 위임하지 않고 새로운 구현을 제공
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(c: Collection<T>): Boolean { // 위임하지 않고 새로운 구현을 제공
        objectsAdded += c.size
        return innerSet.addAll(c)
    }
}

object TempObject {
    val number: Int

    init {
        number = 11
    }
}

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


fun main() {
    val t1 = Temp("a", 1)
    val t2 = Temp("a", 1)
    println(t1 == t2)

    val set = hashSetOf(t1)
    println(set.contains(t2))

    val c1 = Client("aa", 10)
    println(c1)

    val copyC2 = c1.copy(age = 20)
    println(copyC2)

    val cset = CountingSet<Int>()
    cset.addAll(listOf(1,1,2))
    println("${cset.objectsAdded} objects were added ${cset.size} remain")

    val subscribingUser = User.newSubscribingUser("bob@gmail.com")
    val facebookUser = User.newFacebookUser(4)

    val buttonClickListener = object : ClickListener {
        override fun onClick() {
            println("버튼이 클릭되었습니다!")
        }
    }

    buttonClickListener.onClick()



}


