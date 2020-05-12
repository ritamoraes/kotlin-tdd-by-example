package currency

class Sum(
    val augend: Expression,
    val addend: Expression
) : Expression {

    override fun reduce(to: String) =
        Money(amount = augend.reduce(to).amount + addend.reduce(to).amount, currency = to)

    override fun plus(addend: Expression): Expression = Sum(this, addend)

    override fun times(multiplier: Int): Expression = Sum(augend.times(multiplier), addend.times(multiplier))
}
