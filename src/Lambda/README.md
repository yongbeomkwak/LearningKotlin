## 람다식 프로그래밍


### ✅ 정의
람다는 함수에 넘기는 작은 코드 조각 또는 익명 함수를 뜻한다.

반대로 말하면 함수가 이름있는 람다라고 할 수 있다.

---
### 🦸 등장 계기

지바에서는 코드를 함수에 넘길 때 아래와 같이 무명 내부 클래스를 사용하여 넘겼다.

```
button.setOnClickListener(new onClickListener() {
    @Override

    public void onClick(View view) {
        // 동작 코드
    }
});
```

이렇게 되면 재사용을 위한 별도의 처리 과정이 상당히 번거로웠다.

하지만 코틀린에서는 다음과 같이 람다를 이용하면 굉장히 간편해진다.

```kotlin
button.setOnClickListener {  // 동작 코드
}
```
---
### 😄 장점

- 코드의 간결성 증가
    - 불필요한 반복문을 제거하여 재사용성을 극대화

- 퍼포먼스 향상
    - 지연 연산을 지원할 경우 체이닝을 통해 효율성을 증진 시킬 수 있다.

### 😒 단점
- 전부를 순회할 경우, 단순 반복문보다 효율성이 떨어질 수 있다.

- 외부 변수를 사용할 경우, 캡처에서 발생하는 제약을 고려해야하며 `함수 콜스택`을 추적하기 힘들다.

- 협업에서 람다식을 남용하면 코드를 해석하기 오히려 어려울 수 있다.
---
### 🌉 형태

사용하는 형태는 여러가지가 있는데 대표적인 형태만 알아보자.

먼저 무슨 형태든지 다음과 같은 기본형태가 있다.

`파라미터`를 명시하고 `->` 로 구분 이후 `본문`이 오는 형태이다.

또한 파라미터에는 `파라미터 명`과 `타입`을 명시한다.

타입은 생략해도 추론이되지만, 개인적으로는 명시하는게 가독성이 좋은 것 같다.

```kotlin
{ 파라미터 -> 본문 }

list.maxBy { number: Int -> 본문 }
```

#### 1. 괄호 안에 쓰기
```kotlin
list.maxBy({ 파라미터 -> 본문 })
```

#### 2-1. 괄호 밖에 쓰기 (람다 식이 파라미터 가장 마지막에 있어야함)
```kotlin
list.maxBy(){ 파라미터 -> 본문 }
```

#### 2-2. 괄호 없이 쓰기 (람다 식이 파라미터 가장 마지막에 있어야함, 파라미터가 유일하게 람다식 하나뿐뿐)
```kotlin
list.maxBy{ 파라미터 -> 본문 }
```

#### 3 디폴트 파라미터(it) 사용 (파라미터 명을 따로 지정하지 않았을 때)
```kotlin
list.maxBy{ it }
```
---
### 📷 변수 캡쳐

여기서 조금 어려운 개념이 존재한다.
바로 람다식에서 외부 변수를 사용할 때, 해당 변수의 생명주기가 어떻게 될까??

먼저 변수 캡쳐는 불변 변수만 포획할 수 있다. 왜냐하면 가변 변수 일 경우에는
람다안에서 동작의 `사이드 이팩트`가 직접적인 영향을 끼칠 수 있다.

먼저 변수는 크케 `var`, `val`을 사용해서 구분하기 때문에, 람다에서 변수 캡처를 할 때도

어떤 키워드를 사용하는 지에 따라 세부 동작이 달라진다.

#### 1. val 사용 시

먼저 val을 사용했을 때를 살펴보자.
val은 변수에 최초 값을 할당한 이유는 값의 재할당이 불가능한 키워드다.

그렇기 때문에 람다는 `변수`에 할당된 `값`을 복사한다.

```kotlin
val number2: Int = 10
val incr2 = {
    println(number2) // number가 아닌 값 10을 복사
}
```

#### 2. var 사용 시

가변 변수이무로 외부에서 값이 변경될 수 있다.

이 때 코틀린은 Ref클래스 같이 box 객체를 생성하여 감싼다.

이후 람다식과 외부 스코브가 이 Ref를 공유하며 값을 읽고 쓸 수 있다.

만약 아래와 같은 코드가 있다면

```kotlin
    var number1: Int = 0
    val incr = { number1 ++}
```

실제로는 다음과 같이 box객체를 불변 타입으로 만든 후, box 객체 내부의 가변 변수를 변경한다.

람다 입장에서는 불변 변수를 사용하여 `캡쳐` 규칙을 지키지만, `래퍼런스 타입`을 캡쳐했으므로 안에 내용이 변경이 가능하다.

```kotlin
    class Ref<T>(var value: T)

    val counter = Ref(0)
    val incr = { counter.value ++}
    incr()
```

정리하면 람다식 내에서 외부 변수를 사용한다면 최대한 `불변 객체`를 사용하는 것으 권장한다.
아예 안쓰는게 제일 좋긴함

---
### 🖨️ 멤버 참조

#### 등장배경
이람다를 사용해 코드 블록을 다른 함수에게 인자로 너기는 방법을 알아봤다.

하지만 넘기려는 코드가 이미 함수로 선언된 경우는

그 함수를 호출하는 람다를 만들면된다.

하지만 이 구조는 재사용을 위해 중복된 코드가 만들어진다.

이 때 함수를 값으로 바꿔 넘겨주는 방법론이

`멤버 참조`이다.

#### 문법
사용 형태는 `::`를 공통적으로 사용하지만

참조하려는 멤버(프로펕, 메서드)의 위치에 따라 진다.

1. 클래스 멤버 접근
```kotlin
클래스명::멤버명
```
```kotlin
class Bus(speed: Int) {
    val speedLimit: Int = 50

    private var speed: Int

    init {
        this.speed = speed
    }

    fun getSpped(): Int {
        return speed
    }

    fun updateSpped(to: Int) {
        if (to > speedLimit) {
            return
        }
        this.speed = to
    }
}

// 

fun main() {
    var bus = Bus(speed = 20) 
    val update = Bus::updateSpped
    update(bus, 30)
    print(bus.getSpped()) // 30
}
```

2. 클래스 생성자
```kotlin
::클래스명
```

```kotlin
    val makeBus = ::Bus
    val bus2 = makeBus(40)
    print(bus2.getSpped()) // 40
```

3. 최상위 멤버
```kotlin
::멤버
```

```kotlin
fun printHello() = println("Hello")

fun main() {
    val hello = ::printHello
    hello() // hello
}
```

#### 바운드 멤버 참조
1번 클래스 멤버 참조 예제 중, update 함수를 보면
첫번재 매개변수로 인스턴스를 제공했다.

당연하다 어떤 변수의 `Bus`를 업데이트 할지 모르기 때문이다.

이 때 인스턴스를 제공하지 않고 편하게 `멤버 참조`하는 방벙이

바운드 멤버 변수 참조이다.

문법은 클래스명으로 하지 않고 인스턴스 변수 참조한다.

```kotlin
변수::멤버명
```

```kotlin
// 바운드 멤버 변수 
val bus = Bus()
bus::update

update(30)
```

### 🪛 유용한 고차함수

#### 1. filter
- 주어진 람다식 조건으로 컬렉션 원소를 필터링
```kotlin
    val original: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)
    print(original.filter { it % 2 == 0 })
```

#### 2. map
주어진 람다식 결과로 매핑
```kotlin
    val original: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)
    print(original.map { it * it })
```

#### 3. all
- 컬렉션이 전달된 람다 결과를 모두 만족 했는가 (true or false)
```kotlin
    val original: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)
    print(original.all{ it % 2 == 0}) // false
```

#### 4. any
- 컬렉션이 전달된 람다 결과를 하나를 만족했는가 (true or false)
```kotlin
    val original: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)
    print(original.any{ it % 2 == 0}) // true
```

#### 4. any
- 컬렉션이 전달된 람다 결과를 하나를 만족했는가 (true or false)
```kotlin
    val original: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)
    print(original.any{ it % 2 == 0}) // true
```

#### 5. count
- 람다 결과에 만족하는 겷과만 추적
```kotlin
    val original: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)
    print(original.count{ it % 2 == 0 })
```

#### 6. groupBy
- 전달된 람다 결과로 리스트를 여러 그룹으로 이뤄진 맵으로 변환
```kotlin
  val original: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)
  print(original.groupBy{ it % 2 == 0 })  // {false=[1, 3, 5, 7, 9], true=[2, 4, 6, 8, 10]}
```

#### 7. asSequence
- 컬렉션을 시퀀스로 변환
- 이전 컬렉션 고차함수는 체이닝 될 때마다 `즉시 계산`되어 `임시 컬렉션`이 된다.
- 즉 매 연산마다, 모든 원소들을 순회한다.
- 원소 개수가 많아질수록 효율성이 급격히 떨어짐

최종연산 

1. 사용 X
```kotlin
    val original: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)
    val new = original
        .filter { it % 2 == 0 } // 중간 임시 컬렉션 생성 
        .map{ it.toDouble() } // 중간 임시 컬렉션 생성 ..
```

2. 사용 O
```kotlin
    val original: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)
    val new = original
        .asSequence() //시퀀스 생성
        .filter { it % 2 == 0 } // 시퀀스 생성
        .map{ it.toDouble() } // 시퀀스 생성
        .toList() // 최종 연산, 컬렉션생성
```

### 수신 객체 지정 람다 (lambda with receiver)
#### with 함수
- 수신객체를 지정하여 해당 스코프에서는 객체 이름을 반복하지 않아도 된다.

1. 사용 X
```kotlin
fun withOut(): String {
  var numbers = mutableListOf<Int>()
  for (letter in 1..10) {
    numbers.add(letter)
  }

  print(numbers)
  return numbers.toString()
}
```

2. 사용 O
```kotlin
fun  with(): String {
  var numbers = mutableListOf<Int>()

  val result = with(numbers) {
    for (letter in 1..10) {
      add(letter)
    }
    print(this)
    this.toString()
  }
  
  return result
}
```

#### apply 함수
- with와 겅의 동일, 차이점은 apply 결과가 수신객체를 리턴
- 확장함수로 정의돼 있어, 인스턴스에서 `.`으로 시작

```kotlin
fun _apply(): List<Int> {
    var numbers = mutableListOf<Int>()
    val result = numbers.apply {
        for (letter in 1..10) {
            add(letter)
        }
    }

    println(result) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    println(numbers) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    return result
}
```