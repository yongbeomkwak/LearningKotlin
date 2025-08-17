# 제네릭스

## ✅ 정의
클래스, 인터페이스, 함수 등에 사용할 수 있는 

`타입을 매개변수화(parameterize)`하는 문법

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

### 타입 파라미터 제약하기

1. 단일 제약
```kotlin
fun <T: 제약 타입> 함수명(파리미터): 리턴타입
```

2. 다중 제약

where 연산자 이용
```kotlin
fun <T> 함수명(파리미터): 리턴타입 where T: 제약타입1, T: 제약타입 2...
```

## 🔨 실행 시점에서 제네릭스의 동작

### Type Erasure(타입 소거)

#### 1. 장점

> 자바와 마찬가지로 코틀린 제네릭 타입 인자 정보는 런타임에 지워진다.
> 
> 인스턴스를 생성할 때, 타입 인자정보를 유지하지 않으면, 그 객체 자체로 볼 수 있다.

예를 살펴보자.

```kotlin
val list1: List<String> = listOf("a","b")
val list2: List<Int> = listOf(1,2,3)
```
타입 인자 타입은 다르더라도, List객체임은 변함없다.

컴파일러는 두 리스트를 `서로 다른 타입`으로 인식하지만, 런타임에서는 `둘을 완전히 같은 객체`로 인식한다.

이러면 타입 정보를 하는 크기(비용)이 줄기 때문에, 전반적으로 `메모리 사용량`이 줄어든다.

#### 2. 단점

단점은 당연히 타입이 소거되기 때문에, 실행 시점에 타입인자를 검사할 수 없다.

당연히 `is` 연사자를 통해 타입 검사를 할 수 없다.

### 실체화환 타입 파라미터를 사용한 함수 선언

코틀린 제네릭 타입의 타입 인장 정보는 실행 시점에 지워진다.

따라서 재네릭 클래스의 인스턴스가 있어도, 기저 타입을 알 수 없다.

제네릭 타입 함수 역시 마차간지다.

```kotlin
fun <T> isA(value: Any) = value is T 
// Error: Cannot check for instance of erased type: T
```

여기서 약간의 마법을 부리면 타입 추정을 할 수 있다.

준비물은 `inline`과 `reified` 키워드다.

- inline
  - 런타임에 호출한 부분에, 실행코드(바이트 코드)를 그대로 복사
  - 람다 객체 생성 + 호출 스택 추가 오버헤드 ✖️ (성능 최적화)
  - 코드 크기 증가, 추적이 어려울 수 있음

- reified
    - 제네릭 타입 정보를 런타임에도 보존
    - 타입 정보를 inline 실행코드(바이트 코드)에 같이 넘겨줌

```kotlin
inline fun <reified T> isA(value : Any) = value is T
  
fun main(args : Array<String>){
    println(isA<String>("abc")) 		//true
    println(isA<String>(123))			//false
}
```

## 🟰 변성

### ✅ 정의

기저 타입이 같고, 타입 인자가 다른 여러 타입이 서로 어떤 관계가 있는지 설명하는 개념


