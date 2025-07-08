import org.w3c.dom.css.Counter
import java.util.HashMap
import java.util.LinkedList
import java.util.Objects
import javax.print.attribute.standard.MediaSize.Other

class Delegate{
    operator fun getValue(...){
        ...
        // getter를 구현하는 로직을 담는다.
    }

    operator fun setValue(...){
        ...
        // setter를 구현하는 로직을 담는다.
    }
}

data class A(val aa : Int = 10) {
    operator fun plusAssign(o: A) {
        this.aa += o.aa
    }
}

fun main() {
    val a = A()
    val b = A()
    a += b
}
