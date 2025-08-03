import org.w3c.dom.css.Counter
import java.util.HashMap
import java.util.LinkedList
import java.util.Objects
import javax.print.attribute.standard.MediaSize.Other

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

