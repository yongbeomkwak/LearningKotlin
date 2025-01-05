# 클래스

## 정의 

```kotlin
class 클래스명 (
    val 변수명: 타입, // 읽기 전용 
    var 변수명: 타입 ... // 
)
```

## 접근자
```kotlin
class 클래스명 () {
    val 변수명: 타입
        get() {
            return 리턴 값
        }
}
```
getter일 경우 반드시 `val`로 선언 