package su.pank.yamapi.album

import io.ktor.client.request.parameter
import su.pank.yamapi.YamApiClient
import su.pank.yamapi.album.model.Album
import su.pank.yamapi.model.Like

class AlbumsApi(
    private val client: YamApiClient,
) {
    suspend fun withTracks(albumId: Int): Album = client.get("albums", albumId.toString(), "with-tracks")

    suspend fun list(vararg albumIds: Int): List<Album> =
        client.postForm(
            hashMapOf("album-ids" to albumIds.joinToString(",")),
            "albums",
        )

    suspend fun likes(
        userId: Int? = null,
        rich: Boolean = true,
    ): List<Like> =
        client.get(
            "users",
            resolveUserId(userId).toString(),
            "likes",
            "albums",
        ) {
            parameter("rich", rich.toString())
        }

    suspend fun like(
        vararg albumIds: Int,
        userId: Int? = null,
    ): Boolean =
        client.postForm<String, Map<String, String>>(
            hashMapOf("album-ids" to albumIds.joinToString(",")),
            "users",
            resolveUserId(userId).toString(),
            "likes",
            "albums",
            "add-multiple",
        ) == "ok"

    suspend fun unlike(
        vararg albumIds: Int,
        userId: Int? = null,
    ): Boolean =
        client.postForm<String, Map<String, String>>(
            hashMapOf("album-ids" to albumIds.joinToString(",")),
            "users",
            resolveUserId(userId).toString(),
            "likes",
            "albums",
            "remove",
        ) == "ok"

    private suspend fun resolveUserId(userId: Int?): Long =
        userId?.toLong() ?: client.account
            .status()
            .account.uid ?: error("Аккаунт не содержит идентификатор пользователя")
}
