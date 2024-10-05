package rotor

import dsl.client
import io.getenv
import isInvalid
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class RotorTest {
    @Test
    fun parsingListStationIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        client { this.token = token!! }.rotor.stations()
    }

    @Test
    fun creatingNewRotorIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        client { this.token = token!! }.rotor.sessionNew(listOf("user:onyourwave"))
    }
}