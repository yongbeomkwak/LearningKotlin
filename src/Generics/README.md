# 제네릭스

## ✅ 정의
클래스, 인터페이스, 함수 등에 사용할 수 있는 

`타입을 매개변수화(parameterize)`하는 

쉽게 말해, 여러 타입에 대해 재사용 가능한 코드를 만들 수 있게 해준다.

## 🌟 선언 및 사용

선언은 보통 `함수`와 `클래스`에 많이 이용된다.

### 함수

함수는 보통 매개변수 타입을 통해, 재네릭스 타입을 추정한다.

```kotlin
fun <재네릭 타입 매개변수 선언> 함수명(파라미터정의..): 리턴타입 {}
```

```kotlin
fun <T,V> abc (param1: T, param2: V): V {
    return param2
}
```
사용은 2가지 방법으로 나뉜다.

1. <>기호를 토해 먼저 타입을 명시
2. 매개변수값을 바로 전달해 타입 추론 이용

```kotlin
    print(abc<String,Int>("100",10)) // 타입을 미리 명시
    print(abc(10,"100")) // 타입 추론
```
### 클래스

클래스도 마찬가지로, 헤더 부분에 위치한다.

함수와 다른 점은 타입 추론을 이용하긴 힘들고,  인스턴스 생성 시 반드시 타입을 넘겨줘야한다.

```kotlin
class Processor<T> {

    fun process(value: T) {
        print(value?.hashCode())
    }
}

val processer = Processor<Int>()
processer.process(100)
```