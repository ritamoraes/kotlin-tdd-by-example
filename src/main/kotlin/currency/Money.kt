package currency

data class Money(val amount: Int, val currency: String) : Expression {
    override fun reduce(to: String) = this

    companion object {
        fun dollar(amount: Int) = Money(amount = amount, currency = "USD")
        fun franc(amount: Int) = Money(amount = amount, currency = "CHF")
    }
}

fun Money.times(multiplier: Int) = this.copy(amount = multiplier * this.amount)

fun Money.plus(addend: Money) = Sum(this, addend)
