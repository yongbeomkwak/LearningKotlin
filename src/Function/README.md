# 함수

## 기본 선언
```kotlin
fun 함수명 (파리미터명: 타입,...): 리턴 타입 {
    ...
}
```

## default Parameter
기본 값이 있는 파라미터는 최대한 뒤에 명시한다.
```kotlin
fun 함수명 (파라미터명: 타입 = 값): 리턴 타입 {
    
}
```

## @JvmOverloads

자바에서는 함수에 기본값을 넣을 수 없기때문에 

코틀린에서 기본값을 가진 함수를 자바에서 호출할 때,
여러 개의 오버로드된 메서드를 자동으로 생성해 주는 어노테이션입니다.

### 예제
```kotlin
class Greeting {
    @JvmOverloads
    fun sayHello(name: String = "Guest", age: Int = 20) {
        println("Hello, $name! You are $age years old.")
    }
}

//Java 
public class Greeting {
    public void sayHello(String name, int age) {
        System.out.println("Hello, " + name + "! You are " + age + " years old.");
    }

    public void sayHello(String name) {
        sayHello(name, 20); // 기본 age = 20
    }

    public void sayHello() {
        sayHello("Guest", 20); // 기본 name = "Guest", age = 20
    }
}
```
---

## 최상위 함수

코틀린은 일반적으로 함수를 정의하는데 `class`가 필수적이지 않다.

이렇게 class안에 있지 않고 파일에 덩그러니 만들어지면

이런 함수들을 `최상위 함수`라 한다.

이때 이 최상위 함수를 자바로 변환시키면 다음과 같이 변한다.

- 파일이름을 기반으로 클래스로 만든다
- 함수는 `static 함수`가 된다.

```kotlin
// Temp.kt
fun a(variable:Int) : Int {
    return  1
}
```

```java
public final class TempKt {
    public  static Int a(Int variable) {
        return 1
    }
}
```

### @JvmName

JVMName 어노테이션은 코틀린 최상위 함수또는 프로퍼티 를 자바로 만들 때
자동으로 지정되는 클래스 이름을 지정할 수 있다.


```kotlin
// Temp.kt
@JvmName("AUtils")
fun a(variable:Int): Int {
    return 1
}

val aa: Int = 3 

```

```java
public final class AUtils {
    public static Int a(Int variable) {
        return 1;
    }
    
    // val 경우 static 변수에 해당 geteer 생성
    // var 경우 추가적으로 setter도 생성
    public  static final Int aa = 3;

    public static final getAa() {
        return aa;
    }
}
```

## 확장 

### 확장 함수 
```kotlin
fun 확장하려는 타입.확장함수 이름(파라미터): 리턴타입 {
    함수 내용 
}
```

### 확장 프로퍼티 
```kotlin
val 확장하려는 타입.변수명: 변수타입 
    get() = 값 

var 확장하려는 타입.변수명: 변수타입
    get() = 값
    set(value) {
        // Setter 내용
    }
```


### 특징
- 오버라이딩이 불가능하다.


