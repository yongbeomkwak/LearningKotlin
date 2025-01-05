//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
class Person (
    val name: String, // 읽기 전용
    var age: Int
) {
    val isMarried: Boolean
        get() {
            return age > 40
        }

}

fun main() {
    var p = Person("Name", 13)
}
