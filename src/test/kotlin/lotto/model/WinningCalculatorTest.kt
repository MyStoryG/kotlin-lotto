package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class WinningCalculatorTest {
    @Test
    fun `1등 당첨 통계를 계산한다`() {
        assertAll(
            {
                var testTicket = AutomaticLottoTicketGenerator().generate(1)
                val testWinningNumber = testTicket[0].values.toString().replace("[", "").replace("]", "")
                val result =
                    WinningCalculator().generateWinningStatistics(testTicket, LottoTicket(testWinningNumber), 1)
                assertEquals(
                    (LOTTO_MAX_REWARD / (testTicket.size * LOTTO_TICKET_PRICE)).toDouble(),
                    result.rate
                )
            },
            {
                var testTicket = AutomaticLottoTicketGenerator().generate(1)
                var testWinningNumber = testTicket[0].values.toString().replace("[", "").replace("]", "")
                val result =
                    WinningCalculator().generateWinningStatistics(testTicket, LottoTicket(testWinningNumber), 1)
                assertEquals(
                    2000000.0,
                    result.rate
                )
            },
            {
                var testTicket = ManualLottoTicketGenerator().generate(listOf("1, 2, 3, 4, 5, 6"))
                var testWinningNumber = "1, 2, 3, 4, 5, 6"
                val result =
                    WinningCalculator().generateWinningStatistics(testTicket, LottoTicket(testWinningNumber), 1)
                assertEquals(
                    2000000.0,
                    result.rate
                )
            }
        )
    }

    @Test
    fun `2등 당첨 통계를 계산한다`() {
        var testTicket = ManualLottoTicketGenerator().generate(listOf("1, 2, 3, 4, 5, 7"))
        var testWinningNumber = "1, 2, 3, 4, 5, 6"
        val result = WinningCalculator().generateWinningStatistics(testTicket, LottoTicket(testWinningNumber), 7)
        assertEquals(
            30000.0,
            result.rate
        )
    }

    @Test
    fun `3등 당첨 통계를 계산한다`() {
        var testTicket = ManualLottoTicketGenerator().generate(listOf("1, 2, 3, 4, 5, 7"))
        var testWinningNumber = "1, 2, 3, 4, 5, 6"
        val result = WinningCalculator().generateWinningStatistics(testTicket, LottoTicket(testWinningNumber), 8)
        assertEquals(
            1500.0,
            result.rate
        )
    }

    @Test
    fun `4등 당첨 통계를 계산한다`() {
        var testTicket = ManualLottoTicketGenerator().generate(listOf("1, 2, 3, 4, 15, 17"))
        var testWinningNumber = "1, 2, 3, 4, 5, 6"
        val result = WinningCalculator().generateWinningStatistics(testTicket, LottoTicket(testWinningNumber), 8)
        assertEquals(
            50.0,
            result.rate
        )
    }

    @Test
    fun `5등 당첨 통계를 계산한다`() {
        var testTicket = ManualLottoTicketGenerator().generate(listOf("1, 2, 3, 14, 15, 17"))
        var testWinningNumber = "1, 2, 3, 4, 5, 6"
        val result = WinningCalculator().generateWinningStatistics(testTicket, LottoTicket(testWinningNumber), 8)
        assertEquals(
            5.0,
            result.rate
        )
    }

    @Test
    fun `미당첨 통계를 계산한다`() {
        var testTicket = ManualLottoTicketGenerator().generate(listOf("11, 12, 13, 14, 15, 17"))
        var testWinningNumber = "1, 2, 3, 4, 5, 6"
        val result = WinningCalculator().generateWinningStatistics(testTicket, LottoTicket(testWinningNumber), 8)
        assertEquals(
            0.0,
            result.rate
        )
    }

    companion object {
        const val LOTTO_MAX_REWARD = 2_000_000_000
        const val LOTTO_TICKET_PRICE = 1_000
    }
}
