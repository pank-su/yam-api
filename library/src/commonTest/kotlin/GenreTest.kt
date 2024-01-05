import dsl.client
import io.getenv
import kotlinx.coroutines.test.runTest
import model.genre.Genre
import kotlin.test.Test

class GenreTest {
    @Test
    fun gettingTest() = runTest{
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        println(client {
            this.token = token!!
        }.genres().value!!.joinToString(", ") { genre: Genre ->
            genre.id + ", " + (genre.subGenres ?: listOf<Genre>()).joinToString(", ") { it.id }
        })
    }
}