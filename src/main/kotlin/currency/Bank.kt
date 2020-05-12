package currency

class Bank {
    companion object {
        private val rates: MutableMap<Pair<String, String>, Int> = mutableMapOf()

        fun reduce(source: Expression, to: String) = source.reduce(to)

        fun addRate(from: String, to: String, rate: Int) { rates += mapOf(from to to to rate) }

        fun rate(from: String, to: String) = if (from == to) 1 else rates[from to to]
    }
}
