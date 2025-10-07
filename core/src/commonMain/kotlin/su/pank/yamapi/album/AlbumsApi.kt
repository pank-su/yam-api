package su.pank.yamapi.album

import io.ktor.client.request.parameter
import su.pank.yamapi.YamApiClient
import su.pank.yamapi.album.model.Album
import su.pank.yamapi.model.Like

/**
 * API для работы с альбомами.
 *
 * @param client Клиент YamApiClient.
 */
class AlbumsApi(
    private val client: YamApiClient,
) {
    /**
     * Получает альбом с треками.
     *
     * @param albumId Идентификатор альбома.
     * @return Альбом с треками.
     */
    suspend fun withTracks(albumId: Int): Album = client.get("albums", albumId.toString(), "with-tracks")

    /**
     * Получает список альбомов по идентификаторам.
     *
     * @param albumIds Идентификаторы альбомов.
     * @return Список альбомов.
     */
    suspend fun list(vararg albumIds: Int): List<Album> =
        client.postForm(
            hashMapOf("album-ids" to albumIds.joinToString(",")),
            "albums",
        )

    /**
     * Получает лайки альбомов пользователя.
     *
     * @param userId Идентификатор пользователя.
     * @param rich Возвращать ли полную информацию.
     * @return Список лайков.
     */
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

    /**
     * Лайкает альбомы.
     *
     * @param albumIds Идентификаторы альбомов.
     * @param userId Идентификатор пользователя.
     * @return true если успешно.
     */
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

    /**
     * Убирает лайк с альбомов.
     *
     * @param albumIds Идентификаторы альбомов.
     * @param userId Идентификатор пользователя.
     * @return true если успешно.
     */
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
