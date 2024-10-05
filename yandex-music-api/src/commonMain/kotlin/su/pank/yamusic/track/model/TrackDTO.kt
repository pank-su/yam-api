package track.model

import Client
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.album.Album
import model.album.AlbumType
import model.artist.Artist
import model.cover.CoverSize
import track.model.downloadInfo.Codec
import track.model.downloadInfo.DownloadInfo
import utils.IntOrStringSerializer

@Serializable
data class TrackDTO(
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

