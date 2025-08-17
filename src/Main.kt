import org.w3c.dom.css.Counter
import java.util.HashMap
import java.util.LinkedList
import java.util.Objects
import javax.print.attribute.standard.MediaSize.Other
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

interface  A {}
interface  B {}

fun main() {

    fun<T: Comparable<T>> roll(a: T, b: T): Boolean {
        return a > b
    }

    print(roll(10,20))
}

