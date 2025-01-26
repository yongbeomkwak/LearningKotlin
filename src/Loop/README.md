# LOOP

## while과 do-while

### 선언

```kotlin
while (조건) {
    반복할 내용 
}
```

```kotlin
do {
    반복할 내용
} while (조건)
```

### 차이

`while`은 조건에 맞지 않으면 최초 반복도 하지 않지만
`do-while`은 최초반복은 조건에 상관이 실행된다.

---

## for

### 선언

```kotlin

for (변수 in 반복가능한 객체) {
    반복 내용 
}


```

### 같이쓰는 키워드

`a..b`
- a부터 b까지  a <=  i <= b

`a until b`
- a부터 b-1까지 a <= i < b
```kotlin
for (i in 0 until 10) {
        println(i)
}
```

`downTo`
a부터 b까지 내려가라
```kotlin
for (i in 10 downTo 0 ) {
        println(i)
    }
```

`step`
- 반복 간격 조절 
```kotlin
for (i in 0..10 step 2) {
    
}
```

---

## in

### 특정 범위안에 원소가 있는 지 판단한다.

```kotlin
  if (a in 0..9) {
        println("${a}는 0부터 9까지 범위 안에 있습니다.")
    } else {
        println("${a}는 0부터 9까지 범위 안에 없습니다.")
    }

    when(a) {
        in 0..9 -> println("${a}는 0부터 9까지 범위 안에 있습니다.")
        else ->  println("${a}는 0부터 9까지 범위 안에 없습니다.")
    }
```