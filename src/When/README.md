# when

```kotlin

when(조건이 될 재료) {
    조건 -> 행동
}

when {
    (조건이 될 재료 = 조건) -> 행동
}


```

## 예제

### 1. Enum class와 함께

```kotlin
 return when(color) {
        Color.RED -> "apple"
        Color.ORANGE -> "orange"
        else -> "ABC"
    }
```

### 2. Boolean과 함께

```kotlin
 return when {
        (color1 == Color.RED && color2 == Color.YELLOW) -> Color.ORANGE
        else -> Color.BLACK
    }
```

### 3. 타입 확인
```kotlin
interface Fastable // 빠르게 다릴 수 있냐
class Car: Fastable
class AirPlane: Fastable

fun eval(e: Fastable) {
    
    when(e) {
        is Car -> println("e is Car")
        is AirPlane ->  println("e is Airplane")
    }

}
```