package su.pank.yamapi.track

import su.pank.yamapi.YamApiClient
import su.pank.yamapi.album.model.Album
import su.pank.yamapi.album.model.AlbumType
import su.pank.yamapi.model.Artist
import su.pank.yamapi.model.cover.CoverSize
import su.pank.yamapi.track.model.*

/**
 * Представляет музыкальный трек с его метаданными и предоставляет методы для взаимодействия с треком через API.
 *
 * @param client Экземпляр YamApiClient, используемый для выполнения запросов к API.
 * @param trackData Объект TrackData, содержащий метаданные трека.
 */
class Track(
    private val client: YamApiClient,
    trackData: TrackData,
) {
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
    val albums: List<Album> = trackData.albums
    val trackSource: String? = trackData.trackSource
    val major: Major? = trackData.major
    val ogImageUri: String? = trackData.ogImageUri
    val coverUri: String? = trackData.coverUri
    val lyricsAvailable: Boolean? = trackData.lyricsAvailable
    val lyricsInfo: LyricsInfo? = trackData.lyricsInfo
    val derivedColors: DerivedColors? = trackData.derivedColors
    val type: AlbumType? = trackData.type
    val rememberPosition: Boolean? = trackData.rememberPosition
    val trackSharingFlag: TrackSharingFlag? = trackData.trackSharingFlag
    val contentWarning: String? = trackData.contentWarning
    var downloadInfo: List<DownloadInfo>? = trackData.downloadInfo

    fun getUrlOgImage(size: CoverSize) = "https://${ogImageUri?.replace("%%", size.toString())}"

    fun getUrlCover(size: CoverSize) = "https://${coverUri?.replace("%%", size.toString())}"

    private suspend fun fetchDownloadInfo(): List<DownloadInfo> = client.tracks.downloadInfo(this.id)

    suspend fun downloadInfo(
        codec: Codec,
        bitrateInKbps: Int,
    ): DownloadInfo {
        val downloadInfo = this.fetchDownloadInfo()

        return downloadInfo.firstOrNull { it.codec == codec && it.bitrateInKbps == bitrateInKbps }
            ?: downloadInfo.first()
    }

    override fun toString(): String = "Track(id=$id, title=$title, artists=$artists)"
}
