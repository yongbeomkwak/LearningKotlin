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
- 간단히 정리하면 `컴파일아 나중에 꼭 할당할테니깐 얘는 나중에 쓸 때 Null 아니다`
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