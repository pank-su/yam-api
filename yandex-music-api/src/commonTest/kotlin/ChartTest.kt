import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import landing.model.chart.ChartOption
import kotlin.test.Test

class ChartTest {
    @Test
    fun parsingDataIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest


        val chart = client { }.landing.chart(ChartOption.World)
        println(chart)
    }
}