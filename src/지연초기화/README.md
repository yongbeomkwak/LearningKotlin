# 🥊 lateinit vs lazy

## lateinit 

- 말 그대로 늦은 초기화
- 첫번 째 초기화 이후에도 값을 변경할 수 있음 (`var 사용 이유`)
- ⚠️ 초기화하지 않을 경우, 컴파일 에러 발생 ` lateinit property text has not been initialized`

```kotlin
fun main() {
    lateinit var text: String

    // 대충 중간에 뭔가 했음
    val result1 = 30

    text = "Result : $result1"
    println(text)

    // 대충 뭔가 또 했음
    val result2 = 50

    text = "Result : ${result1 + result2}"
    println(text)
}
```

## by lazy

- 다른 값에 의존하여 사용
- 선언 당시에는 초기화를 할 방도가 없지만, 이후에 의존하는 값들이 초기화 된 이후에 값을 채워넣고 싶을 때 사용한다. 
- 첫 호출 시, 초기화 되므로 무거운 객체일수록 최적화에 도움이됨
- 한번의 초기화 이후 불변을 보장하기위해 `val`과 함께 사용

## 한눈에 비교

| 키워드      | 값 변경 가능 | 용법 구분                                    |
|----------|---------|------------------------------------------|
| lateinit | ✅       | 초기화 이후에 계속하여 값이 바뀔 수 있을 때                |
| by lazy  | ❌       | 초기화 이후에 읽기 전용 값으로 사용할 때 (대표적으로 Delegate) | 
