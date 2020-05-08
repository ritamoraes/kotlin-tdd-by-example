package currency

data class Money(val amount: Int, val currency: String) : Expression {
    override fun reduce(bank: Bank, to: String): Money {
        val rate = bank.rate(currency, to) ?: 1
        return Money(amount = amount / rate, currency = to)
    }

    override fun plus(addend: Expression) = Sum(this, addend)

    companion object {
        fun dollar(amount: Int) = Money(amount = amount, currency = "USD")
        fun franc(amount: Int) = Money(amount = amount, currency = "CHF")
    }
}

fun Money.times(multiplier: Int): Expression = this.copy(amount = multiplier * this.amount)

