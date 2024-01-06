import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

class SearchTest {
    @Test
    fun gettingTest() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest

        println(client { this.token = token!! }.search("Гражданская aбоaрона"))
    }

    @Ignore
    @Test
    fun changePageTest() {
        TODO()
    }

    @Test
    fun suggestionGettingTest() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest

        println(
            client { this.token = token!! }.searchSuggest("гражд")
        )
    }
}