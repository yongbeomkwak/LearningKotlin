# 델리게이트 패턴

## 📍개념
나의 일을 `맡기는` 행위

하나의 객체가 하는 일이 많아질 때,  특정 업무를 담당하는 객체를 만들고
그 일을 위임한다.

---
## 예시

### 카페 운영
- 재고 관리
- 음료 준비
- 마케팅
- ...
---

## 🚀 위임의 장점

### ♻️ 코드의 재사용성
나는 카페 사장이다.

새로운 체인점을 오픈 했는다. 가게가 너무 잘된다.

이 때, 다른 지점의 바리스타 파견이 필요하다.

위잌을 이용하면, 다른 체인점으로의 파견이 매우 펺하다.


### 🎯 관심사의 분리

나는 카페 사장으로서, 재고관리, 마케팅이 집중해야되는데,

음료준비까지 한다면, 몸이 2개여도 부족하다.

이 때, 바리스트에게 `음료준비`라는 일을 위임하면, 나는 내 일에만 집중할 수 있다.

### 🥹 확장성과 동적 할당

상황에 따라, 다른 스타일의 바리스타로 바꿔여할 때가 있다.

이때, 델리게이트 패턴을 이용하면, 필요할 때, 동적으로 바리스타 변경이 가능하다.

---


## 🗒️ 문법 

> `위임받는 역할` by `위임 받는 자`

- `by` 키워드를 이용함

## 예제

### 예제1 
- 바리스타 interface 정의
- 각 카페 바리스트 정의
- 각 카페 객체는 바리스타에게, 음료 제조 임무를 위임

```kotlin
// Interface
interface Barista {
    fun makeCoffee()
}

// Delegate
class StarbucksBarista : Barista {
    override fun makeCoffee() {
        println("Make Starbucks-style coffee")
    }
}
// Delegate
class EdiyaBarista : Barista {
    override fun makeCoffee() {
        println("Make Ediya-style coffee")
    }
}

// Delegator: Cafe, Delegate: StarbucksBarista
class StarbucksCafe(): Barista by StarbucksBarista() {}
// Delegator: Cafe, Delegate: EdiyaBarista
class EdiyaCafe(): Barista by EdiyaBarista()

// Usage
fun main() {
    val starbucks = StarbucksCafe()
    starbucks.makeCoffee()

    val ediya = EdiyaCafe()
    ediya.makeCoffee()
}
```

### 예제2
- 바리스타 interface 정의
- 각 카페 바리스트 정의
- 하나의 카페 객체에서 필요한 매장의 바리스타 위임

```kotlin
// Interface
interface Barista {
    fun makeCoffee()
}

// Delegate
class StarbucksBarista : Barista {
    override fun makeCoffee() {
        println("Make Starbucks-style coffee")
    }
}
// Delegate
class EdiyaBarista : Barista {
    override fun makeCoffee() {
        println("Make Ediya-style coffee")
    }
}

// Delegator: Cafe, Delegate: barista
class Cafe(barista: Barista): Barista by barista

// Usage of Cafe
fun main() {
    val starbucks = Cafe(StarbucksBarista())
    starbucks.makeCoffee()

    val ediya = Cafe(EdiyaBarista())
    ediya.makeCoffee()
}
```

## 코틀린 내장 델리게이트
- lazy: 지연 초기화 동작
- Observable: 반응형 프로그래밍
- Vetoable: 특정 조건을 만족할 경우만, 값이 변경