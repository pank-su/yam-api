@file:OptIn(ExperimentalTime::class)

package su.pank.yamapi.album

import io.ktor.client.request.forms.FormDataContent
import io.ktor.http.fullPath
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import su.pank.yamapi.account.model.Account
import su.pank.yamapi.account.model.Status
import su.pank.yamapi.album.model.AlbumData
import su.pank.yamapi.album.model.AlbumType
import su.pank.yamapi.createMockedYamApiClient
import su.pank.yamapi.mockJsonResponse
import su.pank.yamapi.model.Like
import su.pank.yamapi.model.LikeType
import su.pank.yamapi.testJson
import su.pank.yamapi.wrapWithBasicResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class AlbumsApiTest {
    @Test
    fun `list liked albums with rich flag`() =
        runTest {
            val likes =
                listOf(
                    Like(
                        type = LikeType.ALBUM,
                        id = "album-1",
                        album = null,
                        artist = null,
                        playlist = null,
                        shortDescription = null,
                        description = null,
                        isPremiere = null,
                        isBanner = null,
                    ),
                )
            val responseJson = wrapWithBasicResponse(testJson.encodeToString(likes))
            var capturedRich: String? = null

            val yamApiClient =
                createMockedYamApiClient { request ->
//                    assertEquals("/users/123/likes/albums", request.url.fullPath)
                    capturedRich = request.url.parameters["rich"]
                    mockJsonResponse(responseJson)(this, request)
                }

            val api = AlbumsApi(yamApiClient)

            val result = api.likes(userId = 123, rich = false)

            assertEquals("false", capturedRich)
            assertEquals(likes, result)
        }

    @Test
    fun `like album resolves current user`() =
        runTest {
            val okJson = wrapWithBasicResponse("\"ok\"")
            val status =
                Status(
                    account =
                        Account(
                            now = Instant.parse("2025-02-01T00:00:00Z"),
                            uid = 999,
                            login = "album-user",
                            serviceAvailable = true,
                        ),
                    permissions = null,
                )
            val statusJson = wrapWithBasicResponse(testJson.encodeToString(status))
            var capturedAlbumIds: String? = null

            val yamApiClient =
                createMockedYamApiClient { request ->
                    when {
                        request.url.fullPath.contains("account/status") -> mockJsonResponse(statusJson)(this, request)
                        request.url.fullPath.contains("likes/albums/add-multiple") -> {
                            assertEquals("/users/999/likes/albums/add-multiple", request.url.fullPath)
                            val formBody = request.body as FormDataContent
                            capturedAlbumIds = formBody.formData["album-ids"]
                            mockJsonResponse(okJson)(this, request)
                        }
                        else -> error("Unexpected request: ${request.url.fullPath}")
                    }
                }

            val api = AlbumsApi(yamApiClient)

            val result = api.like("321")

            assertTrue(result)
            assertEquals("321", capturedAlbumIds)
        }

    @Test
    fun `fetch album with tracks`() =
        runTest {
            val albumData =
                AlbumData(
                    id = 1,
                    title = "Test Album",
                    type = null,
                    metaType = AlbumType.Music,
                    year = 2024u,
                    releaseDate = null,
                    coverUri = "cover",
                    ogImage = "og",
                    genre = "pop",
                    trackCount = 10,
                    likesCount = 5,
                    recent = false,
                    veryImportant = false,
                    artists = emptyList(),
                    labels = emptyList(),
                    available = true,
                    availableForPremiumUsers = true,
                    availableForOptions = emptyList(),
                    availableForMobile = true,
                    availablePartially = false,
                    bests = emptyList(),
                    trackPosition = null,
                    duplicates = null,
                    volumes = null,
                )
            val responseJson = wrapWithBasicResponse(testJson.encodeToString(albumData))

            val yamApiClient =
                createMockedYamApiClient { request ->
                    assertEquals("/albums/1/with-tracks", request.url.fullPath)
                    mockJsonResponse(responseJson)(this, request)
                }

            val api = AlbumsApi(yamApiClient)

            val result = api.withTracks("1")

            assertEquals(albumData.id.toString(), result.id)

        }
}
