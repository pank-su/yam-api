@file:OptIn(ExperimentalTime::class)

package su.pank.yamapi.album

import su.pank.yamapi.YamApiClient
import su.pank.yamapi.album.model.AlbumData
import su.pank.yamapi.album.model.AlbumType
import su.pank.yamapi.album.model.Label
import su.pank.yamapi.album.model.TrackPosition
import su.pank.yamapi.model.Artist
import su.pank.yamapi.model.Likable
import su.pank.yamapi.model.cover.CoverSize
import su.pank.yamapi.model.cover.WithCover
import su.pank.yamapi.track.Track
import su.pank.yamapi.track.model.Options
import su.pank.yamapi.track.model.TrackData
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * Представляет музыкальный альбом с его метаданными и предоставляет методы для взаимодействия с альбомом через API.
 *
 * @param client Экземпляр YamApiClient, используемый для выполнения запросов к API.
 * @param albumData Объект AlbumData, содержащий метаданные альбома.
 */
class Album(
    private val client: YamApiClient,
    albumData: AlbumData,
) : Likable, WithCover {

    /** Уникальный идентификатор альбома. */
    val id: String = albumData.id.toString()

    /** Название альбома. */
    val title: String = albumData.title

    val type: AlbumType? = albumData.type
    val metaType: AlbumType = albumData.metaType
    val year: UInt? = albumData.year
    val releaseDate: Instant? = albumData.releaseDate
    private val coverUri: String? = albumData.coverUri
    private val ogImage: String = albumData.ogImage
    val genre: String? = albumData.genre

    /** Количество треков в альбоме. */
    val trackCount: Int = albumData.trackCount

    val likesCount: Int? = albumData.likesCount
    val recent: Boolean = albumData.recent
    val veryImportant: Boolean = albumData.veryImportant

    /** Список исполнителей альбома. */
    val artists: List<Artist> = albumData.artists

    val labels: List<Label> = albumData.labels

    /** Доступен ли альбом. */
    val available: Boolean = albumData.available

    val availableForPremiumUsers: Boolean = albumData.availableForPremiumUsers
    val availableForOptions: List<Options> = albumData.availableForOptions
    val availableForMobile: Boolean = albumData.availableForMobile
    val availablePartially: Boolean = albumData.availablePartially
    val bests: List<Int> = albumData.bests
    val trackPosition: TrackPosition? = albumData.trackPosition
    val duplicates: List<AlbumData>? = albumData.duplicates
    val volumes: List<List<TrackData>>? = albumData.volumes

    private var _tracks: List<Track>? = null

    /**
     * Получает список треков альбома.
     *
     * @return Список треков.
     */
    suspend fun tracks(): List<Track> {
        if (_tracks == null) {
            val albumWithTracks = client.albums.withTracks(id)
            _tracks = albumWithTracks.volumes?.flatten()?.map { Track(client, it) } ?: emptyList()
        }
        return _tracks!!
    }

    /**
     * Получает URL обложки альбома указанного размера.
     *
     * @param size Размер обложки.
     * @return URL обложки или null, если обложка отсутствует.
     */
    fun coverUri(size: CoverSize) = if (coverUri != null) "https://${coverUri.replace("%%", size.toString())}" else null

    /**
     * Получает URL OG-изображения альбома указанного размера.
     *
     * @param size Размер изображения.
     * @return URL изображения.
    */
    fun ogImage(size: CoverSize) = "https://${ogImage.replace("%%", size.toString())}"

    override suspend fun like(): Boolean = client.albums.like(id)

    override suspend fun unlike(): Boolean = client.albums.unlike(id)

    override fun toString(): String =
        "Album(id=$id, title='$title', type=$type, metaType=$metaType, year=$year, releaseDate=$releaseDate, coverUri=$coverUri, ogImage='$ogImage', genre=$genre, trackCount=$trackCount, likesCount=$likesCount, recent=$recent, veryImportant=$veryImportant, artists=$artists, labels=$labels, available=$available, availableForPremiumUsers=$availableForPremiumUsers, availableForOptions=$availableForOptions, availableForMobile=$availableForMobile, availablePartially=$availablePartially, bests=$bests, trackPosition=$trackPosition)"
}
