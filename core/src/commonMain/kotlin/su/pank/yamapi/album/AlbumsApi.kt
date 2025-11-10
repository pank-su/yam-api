package su.pank.yamapi.album

import io.ktor.client.request.*
import su.pank.yamapi.YamApiClient
import su.pank.yamapi.album.model.AlbumData
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
    suspend fun withTracks(albumId: String): Album =
        client.get<AlbumData>("albums", albumId, "with-tracks").let { Album(client, it) }

    /**
     * Получает альбом по идентификатору.
     *
     * @param albumId Идентификатор альбома.
     * @return Альбом.
     */
    suspend operator fun get(albumId: String): Album {
        val albumData: AlbumData = client.get("albums", albumId)
        return Album(client, albumData)
    }

    /**
     * Получает список альбомов по идентификаторам.
     *
     * @param albumIds Идентификаторы альбомов.
     * @return Список альбомов.
     */
    suspend fun list(vararg albumIds: Int): List<Album> {
        val albumDataList: List<AlbumData> =
            client.postForm(
                hashMapOf("album-ids" to albumIds.joinToString(",")),
                "albums",
            )
        return albumDataList.map { Album(client, it) }
    }

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
        vararg albumIds: String,
        userId: String? = null,
    ): Boolean =
        client.like<Album>(*albumIds, userId = userId)

    /**
     * Убирает лайк с альбомов.
     *
     * @param albumIds Идентификаторы альбомов.
     * @param userId Идентификатор пользователя.
     * @return true если успешно.
     */
    suspend fun unlike(
        vararg albumIds: String,
        userId: String? = null,
    ): Boolean =
        client.unlike<Album>(*albumIds, userId = userId)

    private suspend fun resolveUserId(userId: Int?): Long =
        userId?.toLong() ?: client.account
            .status()
            .account.uid ?: error("Аккаунт не содержит идентификатор пользователя")
}
