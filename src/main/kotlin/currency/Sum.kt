package currency

class Sum(
    val augend: Money,
    val addend: Money
) : Expression {

    override fun reduce(to: String) = Money(amount = augend.amount + addend.amount, currency = to)
}
