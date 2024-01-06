package model.track

import utils.IntOrStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.album.Album
import model.album.MetaType
import model.artist.Artist
import model.playlist.CoverSize

@Serializable
data class Track(
    @Serializable(with = IntOrStringSerializer::class)
    val id: Int,
    val title: String,
    val available: Boolean,
    val availableForPremiumUsers: Boolean,
    val availableFullWithoutPermission: Boolean,
    val availableForOptions: List<Options>,
    val durationMs: Int,
    val previewDurationMs: Int,
    val storageDir: String,
    val fileSize: Int,
    val r128: R128,
    val artists: List<Artist>,
    val albums: List<Album>,
    val trackSource: String? = null,
    val major: Major? = null,
    @SerialName("ogImage") val ogImageUri: String,
    val coverUri: String,
    val lyricsAvailable: Boolean,
    val lyricsInfo: LyricsInfo? = null,
    val derivedColors: DerivedColors? = null,
    val type: MetaType,
    val rememberPosition: Boolean,
    val trackSharingFlag: TrackSharingFlag? = null
) {
    fun getUrlOgImage(size: CoverSize) = "https://${ogImageUri.replace("%%", size.toString())}"
    fun getUrlCover(size: CoverSize) = "https://${coverUri.replace("%%", size.toString())}"
}

