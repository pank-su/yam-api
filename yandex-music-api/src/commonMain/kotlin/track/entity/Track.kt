package track.entity

import Client
import model.album.Album
import model.album.AlbumType
import model.artist.Artist
import model.cover.CoverSize
import track.model.*
import track.model.downloadInfo.Codec
import track.model.downloadInfo.DownloadInfo

class Track(
    private val client: Client,
    val id: String,
    val title: String,
    val available: Boolean,
    val availableForPremiumUsers: Boolean? = null,
    val availableFullWithoutPermission: Boolean? = null,
    val availableForOptions: List<Options> = listOf(),
    val durationMs: Int? = null,
    val previewDurationMs: Int? = null,
    val storageDir: String? = null,
    val fileSize: Int? = null,
    val r128: R128? = null,
    val artists: List<Artist>,
    val albums: List<Album>,
    val trackSource: String? = null,
    val major: Major? = null,
    private val ogImageUri: String? = null,
    private val coverUri: String? = null,
    val lyricsAvailable: Boolean? = null,
    val lyricsInfo: LyricsInfo? = null,
    val derivedColors: DerivedColors? = null,
    val type: AlbumType? = null,
    val rememberPosition: Boolean? = null,
    val trackSharingFlag: TrackSharingFlag? = null,
    val contentWarning: String? = null
) {

    fun getUrlOgImage(size: CoverSize) = "https://${ogImageUri?.replace("%%", size.toString())}"
    fun getUrlCover(size: CoverSize) = "https://${coverUri?.replace("%%", size.toString())}"

    private suspend fun fetchDownloadInfo(): List<DownloadInfo> {

        return client.tracks.downloadInfo(this.id.toString())
    }

    suspend fun downloadInfo(codec: Codec, bitrateInKbps: Int): DownloadInfo {
        val downloadInfo = this.fetchDownloadInfo()

        return downloadInfo.firstOrNull { it.codec == codec && it.bitrateInKbps == bitrateInKbps }
            ?: downloadInfo.first()
    }
}

fun TrackDTO.toTrack(client: Client): Track = Track(client,
    id,
    title,
    available,
    availableForPremiumUsers,
    availableFullWithoutPermission,
    availableForOptions,
    durationMs,
    previewDurationMs,
    storageDir,
    fileSize,
    r128,
    artists,
    albums,
    trackSource, major, ogImageUri, coverUri, lyricsAvailable, lyricsInfo
)
