# 클래스

## 정의 

```kotlin
class 클래스명 (
    val 변수명: 타입, // 읽기 전용, 기본적으로 private field와 public getter를 만든다.
    var 변수명: 타입 ... // private 필드와 public getter와 setter를 만든다.
)
```

### 접근제한자 
1. `private`
- 오직 class에서 만 
2. `protected`
- 부모 객체가 까지 허용함. (상속 관계)
3. `internal`
- 같은 모듈까지 허용 
4. `public`
- 어디서나 접근 가능하다.


코틀린의 기본 접근 제한자는 `public`

---

## 커스텀 접근자
```kotlin
class 클래스명 () {
    val 변수명: 타입
        get() {
            return 리턴 값
        }
    
    var 변수명: 타입
        get() {
            
        }
    
        set() {
            
        }
}
```
getter만 있을 경우 반드시 `val`로 선언 
setter가 필요하다면 반드시 `var`로 선언

---

## object
object 키워드는 클래스를 정의하면서 동시에 인스턴스를 생성한다.

- `객체 선언`은 클래스 선언과 동시에 단일 인스턴스를 생성
- 즉 싱글톤에 쓰기 좋다.
- 생성자 호출 없이 즉시 생성
- 인터페이스 구현과 상속 가능
- Anonymous Object(무명 객체)로 이용 (`싱글톤`이 아님)

---
## compnion object

클래스 레벨의 메소드 또는 프로퍼티를 갖게 해주는 객체이다.

- 동반 객체는 클래스 이름을 통해 이용한다.
- java의 `static`과 유사
- 인터페이스 구현과 상속 가능
- 동반 객체는 자신을 둘러싼 클래스의 모든 private 멤버에 접근이 가능하다.
- 확장이 가능하다. (`Companion` 키워드 또는 `동반 객체 이름`을 통해)

### 선언

```kotlin
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
```

### 사용

```kotlin
클래스명.CO명.프로퍼티 명 또는 클래스명.프로퍼티 명
```

### 인터페이스 구현 및 상속
```kotlin
interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Person(val name: String) {
    companion object : JSONFactory<Person> {
        // 동반 객체가 인터페이스를 구현한다.
        override fun fromJSON(jsonText: String): Person = /* TODO */
    }
}
```
### 확장 함수 정의
```kotlin
// 비즈니스 로직 모듈
class Person(val firstName: String, val lastName: String) {
    companion object {
        // 비어있는 동반 객체 선언
    }
}

// 클라이언트/서버 통신 모듈
// 확장 함수 선언
fun Person.Companion.fromJSON(json: String): Person {
    /* TODO */
}
```
---

## 상속 접근 제한자 final, open, abstract, override

|    키워드    |    override 가능 여부     |                   설명                    |
|:---------:|:---------------------:|:---------------------------------------:|
|   final   |           X           |           default 제어자 (생략가능)            |
|   open    |     구현부 필수 작성은 아님     |              구현부를 작성할 수 있음              |
| abstract  | override 시 반드시 구현부 작성 |              구현부 작성 할 수 없음              |
| override  |           O           | 기본적으로 open이지만 다음 상속을 맞고 싶으면 앞에 final 명시 | 

```kotlin
class A {} // 상속 불가능

abstract class AA {
    abstract fun a() // abstract 키워드는 abstract 클래스안에서만 가능  
}

open class  AAA: BBB() {
    open fun aaa() {}
    final override fun bbb() {} // AAA를 상속할 때 override 불가 
    }
}
```

---

## 중첩클래스와 내부 클래스

### 특징


|    구분    |           중첩           |           내부            |
|:--------:|:----------------------:|:-----------------------:|
|   키워드    |           X            |          inner          |
|  외부 참조   |          불가능           |     this@OuterClass     |
|  독릭접 생성  | ✅ 가능 (외부 클래스 없이 생성 가능) | ❌ 불가능 (외부 클래스의 인스턴스 필요) |

### 예제 코드
```kotlin
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


fun main() {
    // ✅ NestedClass 인스턴스 생성 (외부 클래스 필요 없음)
    val nested = OuterClass1.NestedClass()
    nested.printOuter() // 출력: "바깥쪽에 대한 참조가 없음"

    // ✅ InnerClass 인스턴스 생성 (외부 클래스의 인스턴스 필요)
    val outer = OuterClass2()
    val inner = outer.InnerClass()
    inner.printOuter() // 출력: "3"

}
```
---

## 봉인된 클래스

### 키워드
`sealed`

### 역할
클래스 계층 정의 시 계층 확장을 제한하는 키워드

### 특징
- sealed는 자동으로 open 클래스
- 컴파일 시점에 디폴트 분기의 불편함을 해결해준다.
- 상위 클래스에 `sealed`가 붙으면 상속한 하위 클래스 정의를 제한
- 하위클래스는 반드시 `중첩 클래스` 형식으로 사용해야한다.

### 예제 코드
```kotlin
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


fun main() {
    val n1 = Num1()
    val s1 = Sum1()
    println(eval1(n1))
    println(eval1(s1))

    val n2 = Expr2.Num2()
    val s2 = Expr2.Sum2()
    println("=========================")
    println(eval2(n2))
    println(eval2(s2))

}
```

## 생성자

### 주 생성자
- 클래스 이름뒤에 오는 괄호로 둘러싸인 부분
- `init`블록이나 프로퍼티 초기화 식에서만 주 생성자 파라미터 참조 가능
- 생성자 파라미터 default 값 지정 가능
- 별도의 어노테이션이나 keyword가 필요하면 주 생서자여도 constructor 키워드 필요

**예제**
```kotlin
// val 또는 var 키워드가 있으면 인스턴스 프로퍼티 명시도 한꺼번에
class User1(val name: String)

// 키워드가 없으면 name은 오직 매개변수 역할만 
class User2 (name: String) {
    private val _name: String

    init {
        this._name = name
    }
}

// 별도의 어노테이션이나 keyword가 필요하면 주 생서자여도 constructor 키워드 필요
class PrivateUser private constructor(val name: String) {

}
```

### 부생성자
- 여러가지 방법으로 초기할 수 있게 지원하는 기능
- `this`키워드를 통해 생성자를 호출 가능
- `super`키워드를 통해 상위 클래스 생성자 호출 가능

**this 를 이용한 부 생성자**
```kotlin
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
```

**상속**
```kotlin
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
```

**super를 이용한 상위 부 생성자 접근**
```kotlin
open  class SuperUser2 {
    constructor(name: String)
    constructor(name: String, age: Int)
}

class User4: SuperUser2 {
    constructor(name: String): super(name) {}
    constructor(name: String, age: Int): super(name,age) {}
}
```
---

## 클래스 필수 정의 메서드

### 1. toString()

- 클래스의 인스턴스 문자열 표현을 정의
- 주로 디버깅과 로깅 시 사용

```kotlin
    override fun toString(): String {
        return "${name} ${age}"
    }
```

### 2. equals()

- 객체의 동등성을 담당함
- `==` or `!=` 연산에 사용됨

```kotlin
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Temp) {
            return false
        }
        return name == other.name && age == other.age
    }
```

### 3. hashCode()
- 두 객체의 해시 동등성 판단
---

## data class
- 오직 데이터를 저장하는 역할만 수행
- 자동으로 필수 메서드를 정의해 줌
- 불변성을 위한 `copy` 메서드를 제공

---
## 클래스 위임 
- 상속을 허용하지 않은 클래스에 새로운 동작을 추가할 때
- `by` 키워드를 통해 클래스 위임 가능

```kotlin
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
```