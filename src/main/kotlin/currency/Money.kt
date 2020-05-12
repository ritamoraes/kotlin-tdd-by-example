package currency

data class Money(val amount: Int, val currency: String) : Expression {
    override fun reduce(to: String): Money {
        val rate = Bank.rate(currency, to) ?: 1
        return Money(amount = amount / rate, currency = to)
    }

    override fun plus(addend: Expression): Expression = Sum(this, addend)

    override fun times(multiplier: Int): Expression = this.copy(amount = multiplier * this.amount)

    companion object {
        fun dollar(amount: Int) = Money(amount = amount, currency = "USD")
        fun franc(amount: Int) = Money(amount = amount, currency = "CHF")
    }
}
