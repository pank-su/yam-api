package su.pank.yamapi.track.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.album.Album
import model.album.AlbumType
import model.artist.Artist
import su.pank.yamapi.utils.IntOrStringSerializer
import track.model.Options
import track.model.downloadInfo.DownloadInfo

@Serializable
data class TrackData(
    @Serializable(with = IntOrStringSerializer::class)
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
    @SerialName("ogImage") val ogImageUri: String? = null,
    val coverUri: String? = null,
    val lyricsAvailable: Boolean? = null,
    val lyricsInfo: LyricsInfo? = null,
    val derivedColors: DerivedColors? = null,
    val type: AlbumType? = null,
    val rememberPosition: Boolean? = null,
    val trackSharingFlag: TrackSharingFlag? = null,
    val contentWarning: String? = null
) {
    var downloadInfo: List<DownloadInfo>? = null


}

enum class TrackSharingFlag {
    VIDEO_ALLOWED, COVER_ONLY
}

@Serializable
data class R128(
    @SerialName("i")
    val i: Double,
    @SerialName("tp")
    val tp: Double
)

@Serializable
data class Major(val id: Int, val name: String)


@Serializable
data class LyricsInfo(
    @SerialName("hasAvailableSyncLyrics")
    val hasAvailableSyncLyrics: Boolean,
    @SerialName("hasAvailableTextLyrics")
    val hasAvailableTextLyrics: Boolean
)