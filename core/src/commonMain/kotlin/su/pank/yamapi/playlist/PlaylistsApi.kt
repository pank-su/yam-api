package su.pank.yamapi.playlist

import su.pank.yamapi.YamApiClient
import su.pank.yamapi.account.model.Visibility
import su.pank.yamapi.model.Like
import su.pank.yamapi.playlist.model.Playlist
import su.pank.yamapi.playlist.model.PlaylistData
import su.pank.yamapi.playlist.model.PlaylistRecommendations

class PlaylistsApi(
    private val client: YamApiClient,
) {

    /**
     * Получает список плейлистов по идентификаторам.
     *
     * @param playlistIds Идентификаторы плейлистов.
     * @return Список плейлистов.
     */
    suspend operator fun invoke(vararg playlistIds: String): List<Playlist> = listByIds(*playlistIds)


    suspend fun list(userId: Int? = null): List<Playlist> =
        client
            .get<List<PlaylistData>>(
                "users",
                resolveUserId(userId).toString(),
                "playlists",
                "list",
            ).map { Playlist(client, it) }

    /**
     * Получает плейлист пользователя.
     *
     * @param kind Вид плейлиста.
     * @param userId Идентификатор пользователя.
     * @return Плейлист.
     *
     * @see Playlist
     */
    suspend fun byKind(
        kind: Int,
        userId: Int? = null,
    ): Playlist =
        Playlist(
            client,
            client.get(
                "users",
                resolveUserId(userId).toString(),
                "playlists",
                kind.toString(),
            ),
        )

    /**
     * Получает плейлисты пользователя.
     *
     * @param kinds Виды плейлистов.
     * @param userId Идентификатор пользователя.
     * @return Список плейлистов.
     */
    suspend fun byKinds(
        vararg kinds: Int,
        userId: Int? = null,
    ): List<Playlist> =
        client
            .postForm<List<PlaylistData>, Map<String, String>>(
                hashMapOf("kinds" to kinds.joinToString(",")),
                "users",
                resolveUserId(userId).toString(),
                "playlists",
            ).map { Playlist(client, it) }

    suspend fun recommendations(
        kind: Int,
        userId: Int? = null,
    ): PlaylistRecommendations =
        client.get(
            "users",
            resolveUserId(userId).toString(),
            "playlists",
            kind.toString(),
            "recommendations",
        )

    suspend fun create(
        title: String,
        visibility: Visibility = Visibility.PUBLIC,
        userId: Int? = null,
    ): Playlist =
        Playlist(
            client,
            client.postForm(
                hashMapOf(
                    "title" to title,
                    "visibility" to visibility.toApiValue(),
                ),
                "users",
                resolveUserId(userId).toString(),
                "playlists",
                "create",
            ),
        )

    suspend fun delete(
        kind: Int,
        userId: Int? = null,
    ): Boolean =
        client.postForm<String, Map<String, String>>(
            emptyMap(),
            "users",
            resolveUserId(userId).toString(),
            "playlists",
            kind.toString(),
            "delete",
        ) == "ok"

    suspend fun rename(
        kind: Int,
        name: String,
        userId: Int? = null,
    ): Playlist =
        Playlist(
            client,
            client.postForm(
                hashMapOf("value" to name),
                "users",
                resolveUserId(userId).toString(),
                "playlists",
                kind.toString(),
                "name",
            ),
        )

    suspend fun changeVisibility(
        kind: Int,
        visibility: Visibility,
        userId: Int? = null,
    ): Playlist =
        Playlist(
            client,
            client.postForm(
                hashMapOf("value" to visibility.toApiValue()),
                "users",
                resolveUserId(userId).toString(),
                "playlists",
                kind.toString(),
                "visibility",
            ),
        )

    suspend fun change(
        kind: Int,
        diff: String,
        revision: Int,
        userId: Int? = null,
    ): Playlist =
        Playlist(
            client,
            client.postForm(
                hashMapOf(
                    "kind" to kind.toString(),
                    "revision" to revision.toString(),
                    "diff" to diff,
                ),
                "users",
                resolveUserId(userId).toString(),
                "playlists",
                kind.toString(),
                "change",
            ),
        )

    suspend fun insertTrack(
        kind: Int,
        trackId: String,
        albumId: String,
        at: Int = 0,
        revision: Int,
        userId: Int? = null,
    ): Playlist =
        change(
            kind,
            """[{"op":"insert","at":$at,"tracks":[{"id":"$trackId","albumId":"$albumId"}]}]""",
            revision,
            userId,
        )

    suspend fun deleteTracks(
        kind: Int,
        from: Int,
        to: Int,
        revision: Int,
        userId: Int? = null,
    ): Playlist =
        change(
            kind,
            """[{"op":"delete","from":$from,"to":$to}]""",
            revision,
            userId,
        )

    suspend fun like(
        vararg playlistIds: String,
        userId: Int? = null,
    ): Boolean =
        client.postForm<String, Map<String, String>>(
            hashMapOf("playlist-ids" to playlistIds.joinToString(",")),
            "users",
            resolveUserId(userId).toString(),
            "likes",
            "playlists",
            "add-multiple",
        ) == "ok"

    suspend fun unlike(
        vararg playlistIds: String,
        userId: Int? = null,
    ): Boolean =
        client.postForm<String, Map<String, String>>(
            hashMapOf("playlist-ids" to playlistIds.joinToString(",")),
            "users",
            resolveUserId(userId).toString(),
            "likes",
            "playlists",
            "remove",
        ) == "ok"

    suspend fun likes(userId: Int? = null): List<Like> =
        client.get(
            "users",
            resolveUserId(userId).toString(),
            "likes",
            "playlists",
        )

    suspend fun listByIds(vararg playlistIds: String): List<Playlist> =
        client
            .postForm<List<PlaylistData>, Map<String, String>>(
                hashMapOf("playlist-ids" to playlistIds.joinToString(",")),
                "playlists",
                "list",
            ).map { Playlist(client, it) }

    suspend fun collectiveJoin(
        userId: Int,
        token: String,
    ): Boolean =
        client.postForm<String, Map<String, String>>(
            emptyMap(),
            "playlists",
            "collective",
            "join",
        ) {
            url {
                parameters.append("uid", userId.toString())
                parameters.append("token", token)
            }
        } == "ok"

    private suspend fun resolveUserId(userId: Int?): Long =
        userId?.toLong() ?: client.account
            .status()
            .account.uid ?: error("Аккаунт не содержит идентификатор пользователя")

    private fun Visibility.toApiValue(): String =
        when (this) {
            Visibility.PUBLIC -> "public"
            Visibility.PRIVATE -> "private"
        }
}
