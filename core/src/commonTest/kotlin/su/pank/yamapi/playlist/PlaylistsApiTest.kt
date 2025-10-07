@file:OptIn(ExperimentalTime::class)

package su.pank.yamapi.playlist

import io.ktor.client.request.forms.FormDataContent
import io.ktor.http.fullPath
import kotlinx.coroutines.test.runTest
import su.pank.yamapi.account.model.Account
import su.pank.yamapi.account.model.Status
import su.pank.yamapi.account.model.User
import su.pank.yamapi.account.model.Visibility
import su.pank.yamapi.createMockedYamApiClient
import su.pank.yamapi.mockJsonResponse
import su.pank.yamapi.playlist.model.PlaylistData
import su.pank.yamapi.testJson
import su.pank.yamapi.wrapWithBasicResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class PlaylistsApiTest {
    @Test
    fun `fetch playlist by kind`() =
        runTest {
            val playlist =
                PlaylistData(
                    owner = User(uid = 111, login = "owner"),
                    cover = null,
                    coverWithoutText = null,
                    madeFor = null,
                    playCounter = null,
                    idForFrom = null,
                    urlPart = "favourites",
                    descriptionFormatted = null,
                    backgroundVideoUrl = null,
                    backgroundImageUrl = null,
                    uid = 111,
                    kind = 999,
                    title = "Favourites",
                    description = "Best tracks",
                    trackCount = 2,
                    tags = listOf("pop"),
                    revision = 3,
                    snapshot = 5,
                    visibility = Visibility.PUBLIC,
                    collective = false,
                    created = Instant.parse("2025-01-01T00:00:00Z"),
                    modified = Instant.parse("2025-01-02T00:00:00Z"),
                    isBanner = false,
                    isPremiere = false,
                    everPlayed = true,
                    durationMs = 123456,
                    ogImageUri = "avatars.yandex.net/get-music-playlist/fake",
                    tracks = emptyList(),
                )
            val responseJson = wrapWithBasicResponse(testJson.encodeToString(playlist))

            val yamApiClient =
                createMockedYamApiClient { request ->
                    assertEquals("/users/111/playlists/999", request.url.fullPath)
                    mockJsonResponse(responseJson)(this, request)
                }

            val api = PlaylistsApi(yamApiClient)

            val result = api.byKind(kind = 999, userId = 111)

            assertEquals(playlist, result.playlistData)
        }

    @Test
    fun `like playlist resolves current user`() =
        runTest {
            val okJson = wrapWithBasicResponse("\"ok\"")
            val status =
                Status(
                    account =
                        Account(
                            now = Instant.parse("2025-01-10T10:00:00Z"),
                            uid = 54321,
                            login = "test",
                            serviceAvailable = true,
                        ),
                    permissions = null,
                )
            val statusJson = wrapWithBasicResponse(testJson.encodeToString(status))
            var capturedPlaylistIds: String? = null

            val yamApiClient =
                createMockedYamApiClient { request ->
                    when {
                        request.url.fullPath.contains("account/status") -> mockJsonResponse(statusJson)(this, request)
                        request.url.fullPath.contains("likes/playlists/add-multiple") -> {
                            assertEquals("/users/54321/likes/playlists/add-multiple", request.url.fullPath)
                            val formBody = request.body as FormDataContent
                            capturedPlaylistIds = formBody.formData["playlist-ids"]
                            mockJsonResponse(okJson)(this, request)
                        }
                        else -> error("Unexpected request: ${request.url.fullPath}")
                    }
                }

            val api = PlaylistsApi(yamApiClient)

            val result = api.like("54321:999")

            assertTrue(result)
            assertEquals("54321:999", capturedPlaylistIds)
        }
}
