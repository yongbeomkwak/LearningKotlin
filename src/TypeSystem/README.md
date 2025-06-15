# 타입 시스템

## ❓ Nullability
### ✅ 문법
`?` 기호를 사용하여 Null 가능성을 컴파일러에게 알려준다.

#### ☕ JAVA
```java
public void strLen(
        @NotNull String s1, 
        @Nullable String s2
) {
    ...
}
```

```kotlin
fun strLen(s1 : String, s2 : String?) {...}
```
---

## 🆘 SafeCall
### ✅ 문법
`?.` 통해 Null 검사와 호출을 동시에 진행, null을 만나면 줃단

```kotlin
fun String.a(): String? {
    println("a")
    return "aaa"
}

fun String.b(): String? {
    println("b")
    return null
}

fun String.c(): String? {
    println("c")
    return "c"
}

fun main() {
    "1".a()?.b()?.c()
}
// OUTPUT:
// a
// b
```
---
## 🪮Elvis Operator
### ✅ 문법
- `?:` 널 복합 연사자
- 삼항 연산자 느낌으로 좌항이 `null`일 경우 우항의 default 값을 할당
```kotlin
fun main() {
    var a: Int? = 3
    println(a ?: 0)
    a = null
    println(a ?: 0)
}
```
---
## ♻️TypeCast
### ✅ 문법
- `as?` 결과는 원하는 타입 또는 null
```kotlin
변수 as? 타입 
```
---
## ⚠️ Not-null Assertion
### ✅ 문법
- null 아님을 단언함, 프로그램 터지는 건 모두 내 잘못
```kotlin
fun String.c(): String? {
    println("c")
    return "c"
}

fun main() {
    var a: Int? = 3
    println(a)
    a = null
    a!!.c() // ❌ 폭파
}
```
---
## 🌉 let
- 단일 변수에 대한 null을 확실히 필터링
- it 인자는 `nullable` 하지 않다고 추론
```kotlin
fun abc(text: String) {
    println("ABC: $text")
}

fun main() {
    var temp: String? = "QWER" 
    temp?.let { abc(it) }
    temp = null
    temp?.let { abc(it) } // ❌ 실행 안됨
}
```

## 👌lateinit 변경자
- Nullable 변수에 대해 불필요한 `Null 체크 하지 않게 만듬`
- 간단히 정리하면 `컴파일러야 나중에 꼭 할당할테니깐 얘는 나중에 쓸 때 Null 아니다`
- 당연히 `var`만 사용 가능
- `Primitive Type (Int, Float, Double, Long)` 사용 불가
```kotlin
class Temp {
    private  lateinit var temp: String
    
    fun abc() {
        println(::temp.isInitialized) // false
        println(temp)  // ❌ lateinit property temp has not been initialized
    }
}

fun main() {
    var instance = Temp()
    instance.abc()
}
```

## ❓플랫폼 타입
- 플랫폼 타입은 Kotlin이 널 관련 정보를 알 수 없는 Java 타입을 말한다.
- Kotlin은 보통 NotNull타입의 값에 대해 널 안정성을 검사하면 경고를 표시하지만 플랫폼 타입의 값에 대해서는 경고하지않음

> ### 플랫폼 타입 도입 이유
>모든 자바 타입을 널이 될 수 있는 타입으로 처리하기엔 널 안정성 검사로 인해 얻는 이익보다
>
> 쓸데없는 비용이 늘어나 프로그래머에게 타입의 널 가능성의 책임을 부여함.


## 🪛 원시 타입(primitive type)

### Int, Boolean
Kotlin은 원시타입과 참조타입을 구분하지 않고, 항상 같은 타입을 사용

대부분, Kotlin의 Int타입은 

Java의 `int(primitive type)` 타입으로 컴파일 되고, Int를 타입인자로 넘기는 경우 `Integer(wrapper class)`로 컴파일된다.

> int vs Integer 비교

| 구분              | int                     | Integer                                |
|-----------------|-------------------------|----------------------------------------|
| 자료형 여부          | 기본 자료형 (Primitive Type) | 참조 자료형 (Reference Type, Wrapper Class) |
| 산술 연산 가능 여부     | 가능                      | Unboxing 후 가능 (자동 변환 필요)               |
| null 값 처리 가능 여부 | 불가능 (null로 초기화 불가)      | 가능 (null 값 처리 가능)                      |
| 제네릭 사용 가능 여부    | X                       | O                                      |
| 저장 공간           | 스택 메모리                  | 참조 값은(포인터) 스택, 실제 값은 힙                 |
| 접근 속도           | 빠름                      | 느림                                     |

### Int?, Boolean?
null이 될 수 있는 원시타입 : Int?, Boolean? 등

코틀린에서 null이 될 수 있는 원시 타입을 사용하면 그 타입은 자바의 래퍼 타입으로 컴파일 된다.

### 🔢 숫자 변환

Kotlin은 Boolean을 제외한 모든 원시 타입에 대한 변환 함수를 제공

- toByte()
- toShort()
- toChar()
- ...

Kotlin은 한 타입의 숫자를 다른 타입으로 자동변환 하지 않음.

결과 타입이 허용하는 숫자의 범위가 원래 타입의 범위보다 넓은 경우조차도 자동 변환은 불가능

명시적으로 변환 메소드를 사용해야함 (toLong(), toInt() ...)

### 🎁  Any, Any? : 최상위 타입

Java는 `Object`, Kotlin은 `Any`가 널이 될 수 없는 타입의 최상위타입

내부적으로 `Any`는 자바의 `java.lang.Object`로 컴파일 된다.

모든 코틀린 클래스에는 toString, equals, hashCode 라는 3개의 메소드가 있다. 

하지만 java.lang.Object에 있는 다른 메소드(wait나 notify 등)는 Any에서 사용할 수 없다. 

그런 메소드를 사용하고 싶다면 java.lang.Object로 캐스트해야 한다.

### 🧊 Unit 타입

Kotlin의 Unit타입은 Java의 void와 같은 기능을 함.
 
- `fun`의 기본 선언은 `void`다.
- 반환 타입으로 Unit을 반환가능
- 타입 매개변수로 Unit을 쓸 수 있다 (void는 불가능)

> ### Kotlin은 왜 void가 아닌 Unit을 만들었을까?
>Unit은 싱글톤 인스턴스이다. 그래서 Kotlin에서 Unit이라는 키워드는 타입이면서도 동시에 객체
>
> Unit은 객체이기도 하기때문에 Any를 상속하는 서브 클래스이다.

### ❌ Nothing 타입

함수가 정상적으로 끝나지 않는 사실을 표현할 때 사용

- Nothing 타입은 아무 값도 포함하지 않음 = return이라는 행위 자체를 하지않음
- 테스트 코드에서 fail 케이스를 하기위해 사용한다.

### 📜 읽기 전용과 변경가능한 컬렉션