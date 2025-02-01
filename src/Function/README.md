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

## 확장 함수 

```kotlin
확장하려는 타입.확장함수 이름(파라미터): 리턴타입 {
    함수 내용 
}
```

### 특징
- 오버라이딩이 불가능하다.
- 
