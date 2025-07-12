# 연산자 오버로딩과 기타관계

> [공식문서](https://kotlinlang.org/docs/operator-overloading.html#in-operator)

## 🔢 산술 연산자

###  이항 연산자
> 연산에 2개의 재료가 필요

| 연산자 기호 | 키워드   |
|--------|-------|
| +      | plus  |
| -      | minus |
| *      | times | 
| /      | div   |                                      
| %      | rem   |

```kotlin
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    operator fun minus(other: Point): Point {
        return Point(x - other.x, y - other.y)
    }

    operator fun times(other: Point): Point {
        return Point(x * other.x, y * other.y)
    }

    operator fun div(other: Point): Point {
        return Point(x / other.x, y / other.y)
    }

    operator fun rem(other: Point): Point {
        return Point(x % other.x, y % other.y)
    }
}

fun main() {
    val aPoint = Point(100, 200)
    val bPoint = Point(200, 100)

    println("plus: ${aPoint + bPoint}")
    println("minus: ${aPoint - bPoint}")
    println("times: ${aPoint * bPoint}")
    println("div: ${aPoint / bPoint}")
    println("rem: ${aPoint % bPoint}")
}
```

### 복함 대입 연산자
> 복합 대입 연산자는, 연산 후 할당까지 함게 지원한다. 

주의할점은, 산술연산자와 복합연사자가 둘다 정의되면 컴파일 오류가 발생한다.

왜냐하면 단순히 산술 연산자만 재정의해도, 복합 대입 연산자를 사용할 수 있다.

차이는 다음과 같다.

산술연산자는 반환객체가 있고, 복합 대입 연산자(...Assign)은 반환 객체가 없으므로

복한 대입 연산자를 굳이 재정의하고 싶으면, 연산에 필요한 파라미터가 `var`로 선언되야한다.

일반적으로는 `복합 대인 연산자`는 **재정의**하지말고, `산술 연산자`를 재정의해서 자동으로 제공되는 복합 대입 연산자를 사용한는 것을 권유한다.


| 연산자 기호 | 키워드         |
|--------|-------------|
| +=     | plusAssign  |
| -=     | minusAssign |
| *=     | timesAssign | 
| /=     | divAssign   |                                      
| %=     | remAssign   |

```kotlin
data class Point(var x: Int, var y: Int) {
    operator fun plusAssign(other: Point) {
        this.x += other.x
        this.y += other.y
    }
}

fun main() {
    var aPoint = Point(100, 200)
    val bPoint = Point(200, 100)

    aPoint += bPoint

    println(aPoint) // (300, 300)
}
```

###  단항 연산자
> 파라미터 없는 연산자, 이항연산자와 마찬지로 리턴 값 있음

여기서 `inc`, `dec` 는 연산자 위치에 따라, 후위 또는 전위로 구분된다. 

| 연산자 기호 | 키워드        |
|--------|------------|
| +      | unaryPlus  |
| -      | unaryMinus |
| !      | not        |
| ++     | inc        |                                      
| --     | dec        |

```kotlin
data class Point(var x: Int, var y: Int) {
    operator fun inc(): Point {
        return Point(this.x + 1, this.y + 1)
    }
}

fun main() {
    var aPoint = Point(100, 200)
    val bPoint = Point(200, 100)

    println(++aPoint) // 101 201
    println(aPoint++) // 101 201
    println(aPoint) // 102, 202
}
```

## 🟰 비교 연산자

### 동등성 연산자
> 같음과 다름을 판단하는 연산자

`!=`을 따로 정의하지 않고 `equals` 값으로 판단

| 연산자 기호 | 키워드    |
|--------|--------|
| ==     | equals |

```kotlin
class Point(val x: Int, val y: Int){
  override equals(obj: Any?): Boolean{
    if(this === obj) return true
    if(obj !is Point) return false
    
    return x == obj.x && y == obj.y
  }
```

### 순서 연산자
> 대소 비교 

`Comparable` 인터페이스의 compareTO 메서드를 이용

```kotlin
data class Point(var x: Int, var y: Int): Comparable<Point> {
override fun compareTo(other: Point): Int {
return compareValuesBy(this, other, Point::x, Point::y) // x우선 비교, 같을 경우 그 다음 y
}
}

fun main() {
var aPoint = Point(100, 200)
val bPoint = Point(200, 100)

    print(aPoint > bPoint)
}
```

## 📇 컬렉션 관련 연산자

### 인덱스 접근 연산자
> 배열처럼 []기호와 index를 써서 읽기, 쓰기를 연산

| 연산자 기호 | 키워드         |
|------------|----------------|
| []         | get<br>set     |

```kotlin
data class Point(var x: Int, var y: Int) {
operator fun get(index: Int): Int {
return when(index) {
0 -> x
1 -> y
else -> throw IndexOutOfBoundsException("Invalid ${index}")
}
}

    operator fun set(index: Int, value: Int) {
        return when(index) {
            0 -> x = value
            1 -> y = value
            else -> throw IndexOutOfBoundsException("Invalid ${index}")
        }
    }
}

fun main() {
var aPoint = Point(100, 200)

    aPoint[0] = 200
    aPoint[1] = 100

    print("${aPoint[0]} ${aPoint[1]}") // 200, 100
}
```

### 📦 in 연산자
> 포함 여부를 판단 하는 연산자

| 연산자 기호 | 키워드         |
|------------|----------------|
| in         | contains     |

```kotlin
data class Point(var x: Int, var y: Int) {

}
data class Rectangle(val upperLeft: Point, val lowerRight: Point) {

    operator fun contains(p: Point): Boolean {
        return p.x in upperLeft.x until lowerRight.x &&
                p.y in upperLeft.y until lowerRight.y
    }
}

fun main() {
    val rect = Rectangle(Point(10, 20), Point(50, 50))
    println(Point(10, 30) in rect) // true
    println(Point(10, 50) in rect) // false
}
```

### 🔹 .. 연산자
> 범위 생성 연산자

| 연산자 기호 | 키워드     |
|--------|---------|
| ..     | rangeTo |

```kotlin
public class Int private constructor() {
    public operator fun rangeTo(other: Int): IntRange   
}
```

추가적으로 코틀린에서는 모든 `Comparable` 인터페이스를 채택하면 rangeTO 함수를 제공한다.

### ♻️ iterator 연산자
> 반복문 순회를 연산자

```kotlin
data class MyNumber(val value: Int) : Comparable<MyNumber> {
    override fun compareTo(other: MyNumber): Int = value.compareTo(other.value)

    operator fun rangeTo(other: MyNumber): MyNumberRange {
        return MyNumberRange(this, other)
    }
}

data class MyNumberRange(
    private val start: MyNumber,
    private val endInclusive: MyNumber
) {
    operator fun iterator(): Iterator<MyNumber> {
        return  object: Iterator<MyNumber> {
            private var current = start

            override fun hasNext(): Boolean = current <= endInclusive

            override fun next(): MyNumber {
                val temp = current
                current = MyNumber(current.value+1)
                return temp
            }
        }
    }
}

fun main() {
    val start = MyNumber(1)
    val end = MyNumber(5)
    val range = start..end

    for (num in range) {
        println(num.value)
    }
}
```

## ⚔ 구조 분해

### 구조 분해 연산자
> 멤버 변수들을 정해진 순서로 분해해서 할당을 도와주는 연산자

`data class`로 만들었을 때는 재정의 하지 않아도, 선언 순서에 따라 자동으로 분해됨


| 연산자 기호 | 키워드        |
|--------|------------|
| () =   | componentN |


```kotlin
data class Person(val name: String, val age: Int)
class Country(val name: String, val population: Int) {

    operator fun component1() = this.name
    operator fun component2() = this.population
}

fun main() {
    val p = Person("aa",10)
    val (name, age) = p // data 클래스는 정의하지 않아도 자동 분해

    println("$name $age")

    var c = Country("Korea", 100) // operator componentN 명시 필요
    val (name2, population) = c

    println("$name2 $population")
}
```

`for 구조문`과 함께 쓰일 때도, `구조 분해`는 강력한 기능을 제공한다.

```kotlin
data class Person(val name: String, val age: Int)

fun main() {

    val people: List<Person> = listOf(Person("a",1), Person("b",2))

    for ((name, age) in people) {
        println("$name $age")
    }
}
```

## 👨‍🔧위임 프로퍼티를 위한 접근자 로직 
> 작업을 직접 수행하지 않고, 도우미 객체가 작업을 처리할 수 있게 하는 패턴을 위임(delegate)라고 함

### 일반적인 위임 방식

다음 연산자를 재정의해서 사용한다.

| 연산자 기호 | 키워드      |
|--------|----------|
| =      | getValue |
| =      | setValue |

```kotlin
class CustomDelegate {

    // 위임 객체 호출
    // thisRef: 호출 객체
    // property: 위임된 프로퍼티 메타데이터
    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): String {
        return "Hello, ${thisRef} '${property.name}' 에서 값을 가져왔어요!"
    }

    // 위임 객체 할당
    // thisRef: 호출 객체
    // property: 위임된 프로퍼티 메타데이터
    // value: 새로 할당된 값
    operator fun setValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>, value: String) {
        println("변수 ${thisRef} '${property.name}' 에 '$value' 를 저장했어요.")
    }
}

class Example {
    var message: String by CustomDelegate()
}

fun main() {
    val e = Example()
    println(e.message) // Hello, Example@3d494fbf 'message' 에서 값을 가져왔어요!
    e.message = "안녕" // 변수 Example@3d494fbf 'message' 에 '안녕' 를 저장했어요.
}
```

```kotlin
// ✅ 컴파일러 후

class Example{
    private val delegate = Delegate()
    val p: String
        set(value: Type) = delegate.setValue(..., value)
    get() = delegate.getValue(...)
}
```

프로퍼티에 위임자를 지정하면 `delegate`를 지정하면  해당 프로퍼티의 `get`과 `set`에

자동으로 `delegate`의 `getValue`와 `getValue`를 자동으로 연결한다.

다음 코드로 편의성을 확인할 수 있다.

```kotlin
// ❌ 위임을 사용하지 않은 코드
class NoDelegateExample {
    private var _message: String = "초기값"

    var message: String
        get() {
            println("[NoDelegate] get message: $_message")
            return _message
        }
        set(value) {
            println("[NoDelegate] set message: $value")
            _message = value
        }
}

fun main() {
    val ex = NoDelegateExample()
    println(ex.message)
    ex.message = "새 메시지"
    println(ex.message)
}
```

```kotlin
// ✅ 위임을 사용한 코드

import kotlin.reflect.KProperty

class LogDelegate {
    private var value: String = "초기값"

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println("[Delegate] get ${property.name}: $value")
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String) {
        println("[Delegate] set ${property.name}: $newValue")
        value = newValue
    }
}

class WithDelegateExample {
    var message1: String by LogDelegate()
    var message2: String by LogDelegate()
}

fun main() {
    val e = Example()
    println(e.message1)
    e.message1 = "안녕"
    e.message2 = "바이"
    println(e.message2)
}
```