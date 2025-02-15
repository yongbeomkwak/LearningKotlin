## Interface

### 정의
변수, 상수, 함수 모두 선언 가능
모두 필수적으로 구현해야함
```kotlin
interface 인터페이스 명 {
    val 변수명: 타입
    var 변수명: 타입
    fun 함수명()
}
```

### 구현 

상속과 마찬가지로 override를 붙혀준다.
```kotlin
class 클래스명: 인터페이스 명 {
    override fun 인터페이스 함수
    override val 변수명
    override var 변수명
}
```

### default 구현
정의부에 기본 값을 선언하면 필수 구현을 하지 않아도 됨
```kotlin
interface 인터페이스 명 {
    fun 함수명() {
        디폴트 내용 
    }
}
```

### 동일한 함수명일 때
`<>` 안에 인터페이스타입 지정
```kotlin
interface Clickable {
    fun showOff() = println("I'm clickable")
}

interface Focusable {
    fun showOff() = println("I'm focusable")
}

class Button: Clickable, Focusable {
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}
```