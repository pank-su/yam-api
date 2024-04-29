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
        client { this.token = token!! }.rotorStations()
    }

    @Test
    fun creatingNewRotorIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        client { this.token = token!! }.rotorSessionNew(listOf("user:onyourwave"))
    }
}