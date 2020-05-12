package currency

interface Expression {
    fun reduce(to: String): Money
    fun plus(addend: Expression): Expression
    fun times(multiplier: Int): Expression
}
