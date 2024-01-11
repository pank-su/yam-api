package track

import dsl.client
import io.getenv
import isInvalid
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import model.track.TrackShort
import kotlin.test.Test

class TrackTest {
    @Test
    fun gettingTest() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        val client = client {
            this.token = token!!
        }
        TrackShort(61885903, Clock.System.now()).fetchTrack(client = client)
    }
}