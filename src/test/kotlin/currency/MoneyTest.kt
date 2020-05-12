package currency

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MoneyTest {

    @Test
    fun `money multiplication`() {
        val fiveDollars = Money.dollar(5)
        assertEquals(Money.dollar(10), fiveDollars.times(2))
        assertEquals(Money.dollar(15), fiveDollars.times(3))
    }

    @Test
    fun `equality`() {
        assertTrue(Money.dollar(5) == Money.dollar(5))
        assertFalse(Money.dollar(5) == Money.dollar(6))
        assertFalse(Money.franc(5) == Money.dollar(5))
    }

    @Test
    fun `currency`() {
        assertEquals("USD", Money.dollar(1).currency)
        assertEquals("CHF", Money.franc(1).currency)
    }

    @Test
    fun `money simple addition`() {
        val fiveDollars = Money.dollar(5)
        val sum = fiveDollars.plus(fiveDollars)
        val reduced = Bank.reduce(sum, "USD")
        assertEquals(Money.dollar(10), reduced)
    }

    @Test
    fun `reduce sum`() {
        val expression = Sum(Money.dollar(3), Money.dollar(4))
        val result = Bank.reduce(expression, "USD")
        assertEquals(Money.dollar(7), result)
    }

    @Test
    fun `reduce money`() {
        val result = Bank.reduce(Money.dollar(1), "USD")
        assertEquals(Money.dollar(1), result)
    }

    @Test
    fun `reduce money with different currency`() {
        Bank.addRate("CHF", "USD", 2)
        val result = Bank.reduce(Money.franc(2), "USD")
        assertEquals(Money.dollar(1), result)
    }

    @Test
    fun `identity rate`() {
        assertEquals(1, Bank.rate("USD", "USD"))
    }

    @Test
    fun `mixed addition`() {
        val fiveDollars: Expression = Money.dollar(5)
        val tenFrancs: Expression = Money.franc(10)
        Bank.addRate("CHF", "USD", 2)
        val result = Bank.reduce(fiveDollars.plus(tenFrancs), "USD")
        assertEquals(Money.dollar(10), result)
    }

    @Test
    fun `sum plus money`() {
        val fiveDollars: Expression = Money.dollar(5)
        val tenFrancs: Expression = Money.franc(10)
        Bank.addRate("CHF", "USD", 2)
        val sum: Expression = Sum(fiveDollars, tenFrancs).plus(fiveDollars)
        val result = Bank.reduce(sum, "USD")
        assertEquals(Money.dollar(15), result)
    }

    @Test
    fun `sum times`() {
        val fiveDollars: Expression = Money.dollar(5)
        val tenFrancs: Expression = Money.franc(10)
        Bank.addRate("CHF", "USD", 2)
        val sum = Sum(fiveDollars, tenFrancs).times(2)
        val result = Bank.reduce(sum, "USD")
        assertEquals(Money.dollar(20), result)
    }
}
