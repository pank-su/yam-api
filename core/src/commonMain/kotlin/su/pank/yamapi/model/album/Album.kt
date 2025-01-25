package su.pank.yamapi.model.album

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import model.album.AlbumType
import model.cover.CoverSize
import su.pank.yamapi.model.Artist
import su.pank.yamapi.track.model.TrackData
import track.model.Options

@Serializable
data class Album(
    val id: Int,
    val title: String,
    val type: AlbumType? = null,
    val metaType: AlbumType,
    val year: UInt? = null,
    val releaseDate: Instant? = null,
    val coverUri: String? = null,
    val ogImage: String,
    val genre: String? = null,
    val trackCount: Int,
    val likesCount: Int? = null,
    val recent: Boolean,
    val veryImportant: Boolean,
    val artists: List<Artist>,
    val labels: List<Label>,
    val available: Boolean,
    val availableForPremiumUsers: Boolean,
    val availableForOptions: List<Options>,
    val availableForMobile: Boolean,
    val availablePartially: Boolean,
    val bests: List<Int>,
    val trackPosition: TrackPosition? = null,
    val duplicates: List<Album>? = null,
    val volumes: List<List<TrackData>>? = null
)  {
    fun getCoverUri(size: CoverSize) =
        if (coverUri != null) "https://${coverUri.replace("%%", size.toString())}" else null
    fun getOgImage(size: CoverSize) = "https://${ogImage.replace("%%", size.toString())}"
}


@Serializable(with = LabelSerializer::class)
data class Label(val id: Int, val name: String)

/**
 * Позиция трека в альбоме
 *
 * @see Album
 */
@Serializable
data class TrackPosition(val volume: Int, val index: Int)
