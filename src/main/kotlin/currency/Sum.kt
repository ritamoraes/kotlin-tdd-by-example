package currency

class Sum(
    val augend: Expression,
    val addend: Expression
) : Expression {

    override fun reduce(bank: Bank, to: String) =
        Money(amount = augend.reduce(bank, to).amount + addend.reduce(bank, to).amount, currency = to)

    override fun plus(addend: Expression): Expression = Sum(this, addend)

    override fun times(multiplier: Int): Expression  = Sum(augend.times(multiplier), addend.times(multiplier))
}
