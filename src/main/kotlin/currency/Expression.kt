package currency

interface Expression {
    fun reduce(to: String): Money
}
