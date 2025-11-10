package su.pank.yamapi.playlist

import su.pank.yamapi.YamApiClient
import su.pank.yamapi.account.model.Visibility
import su.pank.yamapi.model.Like
import su.pank.yamapi.playlist.Playlist
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


    suspend fun list(userId: String? = null): List<Playlist> =
        client
            .get<List<PlaylistData>>(
                "users",
                client.resolveUserId(userId),
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
        userId: String? = null,
    ): Playlist =
        Playlist(
            client,
            client.get(
                "users",
                client.resolveUserId(userId),
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
        userId: String? = null,
    ): List<Playlist> =
        client
            .postForm<List<PlaylistData>, Map<String, String>>(
                hashMapOf("kinds" to kinds.joinToString(",")),
                "users",
                client.resolveUserId(userId),
                "playlists",
            ).map { Playlist(client, it) }

    suspend fun recommendations(
        kind: Int,
        userId: String? = null,
    ): PlaylistRecommendations =
        client.get(
            "users",
            client.resolveUserId(userId),
            "playlists",
            kind.toString(),
            "recommendations",
        )

    suspend fun create(
        title: String,
        visibility: Visibility = Visibility.PUBLIC,
        userId: String? = null,
    ): Playlist =
        Playlist(
            client,
            client.postForm(
                hashMapOf(
                    "title" to title,
                    "visibility" to visibility.name.lowercase(),
                ),
                "users",
                client.resolveUserId(userId),
                "playlists",
                "create",
            ),
        )

    suspend fun delete(
        kind: Int,
        userId: String? = null,
    ): Boolean =
        client.postForm<String, Map<String, String>>(
            emptyMap(),
            "users",
            client.resolveUserId(userId),
            "playlists",
            kind.toString(),
            "delete",
        ) == "ok"

    suspend fun rename(
        kind: Int,
        name: String,
        userId: String? = null,
    ): Playlist =
        Playlist(
            client,
            client.postForm(
                hashMapOf("value" to name),
                "users",
                client.resolveUserId(userId),
                "playlists",
                kind.toString(),
                "name",
            ),
        )

    suspend fun changeVisibility(
        kind: Int,
        visibility: Visibility,
        userId: String? = null,
    ): Playlist =
        Playlist(
            client,
            client.postForm(
                hashMapOf("value" to visibility.name.lowercase()),
                "users",
                client.resolveUserId(userId),
                "playlists",
                kind.toString(),
                "visibility",
            ),
        )

    suspend fun change(
        kind: Int,
        diff: String,
        revision: Int,
        userId: String? = null,
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
                client.resolveUserId(userId),
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
        userId: String? = null,
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
        userId: String? = null,
    ): Playlist =
        change(
            kind,
            """[{"op":"delete","from":$from,"to":$to}]""",
            revision,
            userId,
        )

    suspend fun like(
        vararg playlistIds: String,
        userId: String? = null,
    ): Boolean =
        client.like<Playlist>(*playlistIds, userId=userId)

    suspend fun unlike(
        vararg playlistIds: String,
        userId: String? = null,
    ): Boolean =
        client.unlike<Playlist>(*playlistIds, userId = userId)

    suspend fun likes(userId: String? = null): List<Like> =
        client.get(
            "users",
            client.resolveUserId(userId),
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



}
