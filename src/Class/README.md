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

## Compnion Object

클래스 레벨의 메소드 또는 프로퍼티를 갖게 해주는 객체이다.

### 선언

```kotlin
class AClass {
    companion object { 
        
    }
    
    companion object 이름 {
        
    }
}
```

### 사용

```kotlin
클래스명.CO명.프로퍼티 명 또는 클래스명.프로퍼티 명
```

---

### 상속 접근 제한자 final, open, abstract, override

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
