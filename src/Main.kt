import org.w3c.dom.css.Counter
import java.util.HashMap
import java.util.LinkedList
import java.util.Objects
import javax.print.attribute.standard.MediaSize.Other

class CustomDelegate {

    // thisRef: 호출 객체
    // property: 위임된 프로퍼티 메타데이터
    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): String {
        return "Hello, ${thisRef} '${property.name}' 에서 값을 가져왔어요!"
    }

    // thisRef: 호출 객체
    // property: 위임된 프로퍼티 메타데이터
    // value: 새로 할당된 값
    operator fun setValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>, value: String) {
        println("변수 ${thisRef} '${property.name}' 에 '$value' 를 저장했어요.")
    }
}

class Example {
    var message1: String by CustomDelegate()
    var message2: String by CustomDelegate()
}

fun <T,V> abc (param1: T, param2: V): V {
    return param2
}

class Processor<T> {

    fun process(value: T) {
        print(value?.hashCode())
    }
}

fun main() {
    val e = Example()
    println(e.message1)
    e.message1 = "안녕"
    e.message2 = "바이"
    println(e.message2)

    print(abc<String,Int>("100",10)) // 타입을 미리 명시
    print(abc(10,"100")) // 타입 추론

    val processer = Processor<Int>()
    processer.process(100)


}
