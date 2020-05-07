package currency

data class Money(val amount: Int, val currency: String) : Expression {
    override fun reduce(bank: Bank, to: String): Money {
        val rate = bank.rate(currency, to) ?: 1
        return Money(amount = amount / rate, currency = to)
    }

    companion object {
        fun dollar(amount: Int) = Money(amount = amount, currency = "USD")
        fun franc(amount: Int) = Money(amount = amount, currency = "CHF")
    }
}

fun Money.times(multiplier: Int) = this.copy(amount = multiplier * this.amount)

fun Money.plus(addend: Money) = Sum(this, addend)
