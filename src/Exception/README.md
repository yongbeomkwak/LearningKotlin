# Exception

## 예외 발생시키기

```kotlin
throw SomeError 
```
## 예외 처리

### try-catch
```kotlin
try {
    예외 발생 가능성 있는 코드 
} catch(e: 발생가능한 에러) {
    에러 처리 
}
```

### try-catch-finally
```kotlin
try {
    예외 발생 가능성 있는 코드 
} catch(e: 발생가능한 에러) {
    에러 처리 
} finally {
    예외가 발생하든 안하든 실행되는 코드
}
```
