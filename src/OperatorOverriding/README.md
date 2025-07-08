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
