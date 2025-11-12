package su.pank.yamapi.track

import su.pank.yamapi.YamApiClient
import su.pank.yamapi.album.Album
import su.pank.yamapi.album.model.AlbumType
import su.pank.yamapi.downloadInfo.DownloadInfo
import su.pank.yamapi.model.Artist
import su.pank.yamapi.model.Likable
import su.pank.yamapi.model.cover.CoverSize
import su.pank.yamapi.model.cover.WithCover
import su.pank.yamapi.track.model.*
import su.pank.yamapi.track.model.lyrics.Lyrics
import su.pank.yamapi.track.model.lyrics.LyricsFormat

/**
 * Представляет музыкальный трек с его метаданными и предоставляет методы для взаимодействия с треком через API.
 *
 * @param client Экземпляр YamApiClient, используемый для выполнения запросов к API.
 * @param trackData Объект TrackData, содержащий метаданные трека.
 */
class Track(
    private val client: YamApiClient,
    trackData: TrackData,
) : Likable, WithCover {
    /** Уникальный идентификатор трека. */
    val id: String = trackData.id

    /** Название трека. */
    val title: String = trackData.title

    /** Доступен ли трек. */
    val available: Boolean = trackData.available
    val availableForPremiumUsers: Boolean? = trackData.availableForPremiumUsers
    val availableFullWithoutPermission: Boolean? = trackData.availableFullWithoutPermission
    val availableForOptions: List<Options> = trackData.availableForOptions
    val durationMs: Int? = trackData.durationMs
    val previewDurationMs: Int? = trackData.previewDurationMs
    val storageDir: String? = trackData.storageDir
    val fileSize: Int? = trackData.fileSize
    val r128: R128? = trackData.r128

    /** Список исполнителей, связанных с треком. */
    val artists: List<Artist> = trackData.artists

    /** Список альбомов, содержащих трек. */
    val albums: List<Album> = trackData.albums.map { Album(client, it) }
    val trackSource: String? = trackData.trackSource
    val major: Major? = trackData.major
    private val ogImageUri: String? = trackData.ogImageUri
    private val coverUri: String? = trackData.coverUri
    val lyricsAvailable: Boolean? = trackData.lyricsAvailable
    val lyricsInfo: LyricsInfo? = trackData.lyricsInfo
    val derivedColors: DerivedColors? = trackData.derivedColors
    val type: AlbumType? = trackData.type
    val rememberPosition: Boolean? = trackData.rememberPosition
    val trackSharingFlag: TrackSharingFlag? = trackData.trackSharingFlag
    val contentWarning: String? = trackData.contentWarning


    fun urlOgImage(size: CoverSize) = buildImageUrl(ogImageUri, size)

    fun urlCover(size: CoverSize) = buildImageUrl(coverUri, size)

    suspend fun downloadInfo(): List<DownloadInfo> = client.tracks.downloadInfo(this.id)

    override fun toString(): String =
        "Track(id=$id, title=$title, available=$available, availableForPremiumUsers=$availableForPremiumUsers, availableFullWithoutPermission=$availableFullWithoutPermission, availableForOptions=$availableForOptions, durationMs=$durationMs, previewDurationMs=$previewDurationMs, storageDir=$storageDir, fileSize=$fileSize, r128=$r128, artists=$artists, albums=$albums, trackSource=$trackSource, major=$major, ogImageUri=$ogImageUri, coverUri=$coverUri, lyricsAvailable=$lyricsAvailable, lyricsInfo=$lyricsInfo, derivedColors=$derivedColors, type=$type, rememberPosition=$rememberPosition, trackSharingFlag=$trackSharingFlag, contentWarning=$contentWarning)"

    override suspend fun like(): Boolean = client.like<Track>(id)

    override suspend fun unlike(): Boolean = client.unlike<Track>(id)

    suspend fun lyrics(format: LyricsFormat = LyricsFormat.TEXT): Lyrics = client.tracks.lyrics(id, format)
}
