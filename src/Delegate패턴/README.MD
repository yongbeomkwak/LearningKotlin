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

위잌을 이용하면, 다른 체인점으로의 파견이 매우 편하다.


### 🎯 관심사의 분리

나는 카페 사장으로서, 재고관리, 마케팅이 집중해야되는데,

음료준비까지 한다면, 몸이 2개여도 부족하다.

이 때, 바리스트에게 `음료준비`라는 일을 위임하면, 나는 내 일에만 집중할 수 있다.

### 🥹 확장성과 동적 할당

상황에 따라, 다른 스타일의 바리스타로 바꿔여할 때가 있다.

이때, 델리게이트 패턴을 이용하면, 필요할 때, 동적으로 바리스타 변경이 가능하다.

---


## 🗒️ 문법 

> `위임받는 역할` by `위임자`

- `by` 키워드를 이용함

### Kotlin `by` 키워드 비교

| 구문 형태                              | 종류 | 의미 | 호출 시점 | 예시 |
|------------------------------------|------|------|---------|------|
| `: Interface by obj`               | 인터페이스 구현 위임 | 메서드 실행을 다른 객체에 모두 위임 | **컴파일 시** 메서드 연결 코드 생성 | `class Mart : Payment by payment` |
| `val x: Type by delegate`          | 프로퍼티 위임 |  속성의 값을 읽거나 쓰는 두 가지 동작을 다른 객체에 위임 | **런타임**에 `getValue` / `setValue` 호출 | `val mart: Mart by MartDelegate()` |

### 💡 비유

- 인터페이스 구현 위임:
  -   “나는 마트 사장이지만, 결제 업무는 결제팀장에게 맡길게.”

  
- 프로퍼티 위임:
  - “마트가 필요할 때마다 내가 전화해서 불러줄게. (필요할 때만 가져옴)”



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
- 하나의 카페에세 , 바리스트의 역할을 위임

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

### 예제 3

```kotlin
// 부동산과 관련된 권한들
interface Authority {
    fun stampSeal() // 도장 찍기
    ... // 기타 등등
}

// 부동산에 대해서 내가 행사할 수 있는 권리가 있어!
class Customer(private val name: String) : Authority {
    // Authority 인터페이스 구현
    override fun stampSeal() {
        println("${name} 도장 쾅")
    }
}

// 저는 중개인 입니다. 제가 대신 손님의 집을 알아봐드릴게요
class Broker(private val customer: Authority) : Authority by customer {
	// by 키워드를 통해 customer 파라미터에게 권한을 위임
}

fun main() {
    //개발자K씨
    val developerK = Customer("개발자K씨")
    //부동산중개인G씨
    val brokerG = Broker(developerK)

    bokerG.stampSeal() // 개발자K씨에게 권한을 위임받아서 도장찍겠습니다 ㅎㅎ
}
```

```kotlin
import kotlin.reflect.KProperty

// 도장을 찍을 수 있는 권한 인터페이스
interface Authority {
    fun stampSeal()
}

// Customer: 실제 도장을 찍을 수 있는 사람
class Customer(private val name: String) : Authority {
    // Authority 구현
    override fun stampSeal() {
        println("${name} 도장 쾅")
    }

    // 'by' 문법에서 변수를 초기화할 때 호출되는 함수
    // 예: val brokerG: Broker by Customer("K") → Customer.getValue(...) 호출됨
    operator fun getValue(nothing: Nothing?, property: KProperty<*>): Broker {
        // Broker에게 자기 자신(Customer)의 권한을 넘겨서 위임 객체를 만들어 반환
        return Broker(this)
    }
}

// Broker: Authority 역할을 하지만, 실제 구현은 Customer에게 위임
class Broker(private val customer: Authority) : Authority by customer
// ↑ 'Authority by customer' → Broker.stampSeal() 호출 시 customer.stampSeal() 실행됨

fun main() {
    // val brokerG: Broker by Customer("K")
    // 1. Customer("K").getValue(...) 호출됨
    // 2. getValue가 Broker(Customer("K"))를 반환 → brokerG 변수에 할당
    val brokerG: Broker by Customer("K")

    // brokerG.stampSeal() 호출
    // → Broker가 Authority를 customer(Customer("K"))에게 위임했으므로
    // → 결국 Customer("K").stampSeal() 실행
    // 출력: "K 도장 쾅"
    brokerG.stampSeal()
}
```

### 예제 5

```kotlin
import kotlin.reflect.KProperty

// 🛒 결제 권한
interface Payment {
    fun processPayment(amount: Int)
}

// 📦 물류 권한
interface Logistics {
    fun deliverItem(item: String)
}

// 💳 결제 담당자
class PaymentManager(private val name: String) : Payment {
    override fun processPayment(amount: Int) {
        println("$name: 결제 $amount 원 완료")
    }
}

// 🚚 물류 담당자
class LogisticsManager(private val name: String) : Logistics {
    override fun deliverItem(item: String) {
        println("$name: $item 배송 완료")
    }
}

// 🏬 대형마트 - 결제와 물류를 각각 위임
class Mart(payment: Payment, logistics: Logistics)
    : Payment by payment, Logistics by logistics

// 💼 Mart를 getValue로 생성해주는 대행자
class MartDelegate(private val martName: String) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Mart {
        println("MartDelegate: $martName 마트를 준비합니다...")
        return Mart(
            PaymentManager("$martName 결제팀"),
            LogisticsManager("$martName 물류팀")
        )
    }
}

fun main() {
    // MartDelegate가 실제 Mart를 생성해줌
    val emart: Mart by MartDelegate("이마트")

    println("=== 마트 업무 시작 ===")
    emart.processPayment(50000)   // 결제팀에게 위임
    emart.deliverItem("TV")       // 물류팀에게 위임
}
```


## 코틀린 내장 델리게이트
- lazy: 지연 초기화 동작
- Observable: 반응형 프로그래밍
- Vetoable: 특정 조건을 만족할 경우만, 값이 변경