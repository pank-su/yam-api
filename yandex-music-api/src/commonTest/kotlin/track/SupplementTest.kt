package track

import dsl.client
import io.getenv
import isInvalid
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class SupplementTest {
    @Test
    fun parsingDataIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        client {
            this.token = token!!
        }.trackSupplement(25427091)
    }
}