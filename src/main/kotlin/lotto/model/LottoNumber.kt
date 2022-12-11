package lotto.model

import java.lang.NumberFormatException

class LottoNumber(input: String) {
    val number = generateLottoNumber(input)

    private fun generateLottoNumber(input: String): Int {
        checkValidNumber(input)
        checkValidRange(input)
        checkNegative(input)

        return input.toInt()
    }

    private fun checkValidNumber(number: String) {
        try {
            number.toInt()
        } catch (error: NumberFormatException) {
            throw IllegalArgumentException("숫자만 입력 가능합니다.")
        }
    }

    private fun checkValidRange(number: String) {
        require(number.toInt() in LOTTO_WINNER_NUMBER_RANGE) { "1에서 45 사이의 값을 입력하세요." }
    }

    private fun checkNegative(number: String) {
        val isPositive = number.toInt() >= 0
        require(isPositive) { "음수 입력 불가" }
    }

    companion object {
        const val LOTTO_NUMBER_SIZE = 6
        val LOTTO_WINNER_NUMBER_RANGE = 1..45
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is LottoNumber) return false
        return number == other.number
    }

    override fun hashCode(): Int = number.hashCode() * 31
}
