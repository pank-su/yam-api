package model.track

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.album.Album
import model.album.MetaType
import model.artist.Artist
import model.playlist.CoverSize

@Serializable
enum class ContentWarningType {
    @SerialName("explicit")
    Explicit
}

@Serializable
enum class Options {
    @SerialName("bookmate")
    Bookmate
}

enum class TrackSharingFlag {
    VIDEO_ALLOWED, COVER_ONLY
}

@Serializable
data class Track(
    val id: String,
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
    val lyricsInfo: LyricsInfo,
    val derivedColors: DerivedColors,
    val type: MetaType,
    val rememberPosition: Boolean,
    val trackSharingFlag: TrackSharingFlag
) {
    fun getUrlOgImage(size: CoverSize) = "https://${ogImageUri.replace("%%", size.toString())}"
    fun getUrlCover(size: CoverSize) = "https://${coverUri?.replace("%%", size.toString())}"
}
