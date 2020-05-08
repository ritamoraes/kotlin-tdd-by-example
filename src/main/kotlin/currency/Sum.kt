package currency

class Sum(
    val augend: Expression,
    val addend: Expression
) : Expression {

    override fun reduce(bank: Bank, to: String) =
        Money(amount = augend.reduce(bank, to).amount + addend.reduce(bank, to).amount, currency = to)

    override fun plus(addend: Expression): Expression {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
