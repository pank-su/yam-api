@file:OptIn(ExperimentalTime::class)

package su.pank.yamapi.track

import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import su.pank.yamapi.account.model.Account
import su.pank.yamapi.account.model.Status
import su.pank.yamapi.createMockedYamApiClient
import su.pank.yamapi.mockJsonResponse
import su.pank.yamapi.model.Revision
import su.pank.yamapi.testJson
import su.pank.yamapi.track.model.TrackData
import su.pank.yamapi.wrapWithBasicResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class TracksApiTest {
    @Test
    fun `test getting tracks`() =
        runTest {
            val expectedTracks =
                listOf(
                    TrackData(
                        id = "123",
                        title = "Test Track",
                        artists = emptyList(),
                        albums = emptyList(),
                        available = true,
                        contentWarning = null,
                    ),
                )
            val responseJson = wrapWithBasicResponse(testJson.encodeToString(expectedTracks))
            val yamApiClient = createMockedYamApiClient(mockJsonResponse(responseJson))
            val tracksApi = TracksApi(yamApiClient)

            val actualTracks = tracksApi("123")

            assertEquals(1, actualTracks.size)
            val actualTrack = actualTracks.first()
            val expectedTrack = expectedTracks.first()
            assertEquals(expectedTrack.id, actualTrack.id)
            assertEquals(expectedTrack.title, actualTrack.title)
            assertEquals(expectedTrack.artists, actualTrack.artists)
            assertEquals(expectedTrack.albums, actualTrack.albums)
            assertEquals(expectedTrack.available, actualTrack.available)
            assertEquals(expectedTrack.contentWarning, actualTrack.contentWarning)
        }

    @Test
    fun `test liking a track`() =
        runTest {
            val expectedRevision = Revision(1)
            val revisionJson = wrapWithBasicResponse(testJson.encodeToString(expectedRevision))

            val status =
                Status(
                    account =
                        Account(
                            now = Instant.parse("2025-09-16T12:00:00Z"),
                            uid = 12345,
                            login = "testuser",
                            serviceAvailable = true,
                        ),
                    permissions = null,
                )
            val statusJson = wrapWithBasicResponse(testJson.encodeToString(status))

            val yamApiClient =
                createMockedYamApiClient { request ->
                    when {
                        request.url.fullPath.contains("account/status") -> mockJsonResponse(statusJson)(this, request)
                        else -> mockJsonResponse(revisionJson)(this, request)
                    }
                }

            val tracksApi = TracksApi(yamApiClient)

            val actualRevision = tracksApi.like(54321)

            assertEquals(expectedRevision, actualRevision)
        }

    @Test
    fun `test unliking a track`() =
        runTest {
            val expectedRevision = Revision(2)
            val revisionJson = wrapWithBasicResponse(testJson.encodeToString(expectedRevision))

            val status =
                Status(
                    account =
                        Account(
                            now = Instant.parse("2025-09-16T12:00:00Z"),
                            uid = 12345,
                            login = "testuser",
                            serviceAvailable = true,
                        ),
                    permissions = null,
                )
            val statusJson = wrapWithBasicResponse(testJson.encodeToString(status))

            val yamApiClient =
                createMockedYamApiClient { request ->
                    when {
                        request.url.fullPath.contains("account/status") -> mockJsonResponse(statusJson)(this, request)
                        else -> mockJsonResponse(revisionJson)(this, request)
                    }
                }

            val tracksApi = TracksApi(yamApiClient)

            val actualRevision = tracksApi.unlike(54321)

            assertEquals(expectedRevision, actualRevision)
        }
}
