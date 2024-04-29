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
    fun parsingDataIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        val client = client {
            this.token = token!!
        }
        TrackShort(45792354, Clock.System.now()).fetchTrack(client = client)
    }

    @Test
    fun likeTrackIsValid() = runTest {
        val token = getenv("token")
        if (token.isInvalid()) return@runTest
        val client = client {
            this.token = token!!
        }
        client.likeTracks(111008434)
    }
}