package track

import dsl.client
import io.getenv
import isInvalid
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class SimilarTest {
    @Test
    fun parsingDataIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        println(client { }.tracksSimilar(109140203).similarTracks)
    }
}