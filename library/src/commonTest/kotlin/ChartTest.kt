import dsl.client
import kotlinx.coroutines.test.runTest
import model.landing.ChartOption
import kotlin.test.Test

class ChartTest {
    @Test
    fun gettingTest() = runTest {
        val chart = client { }.chart(ChartOption.World)
        println(chart)
    }
}