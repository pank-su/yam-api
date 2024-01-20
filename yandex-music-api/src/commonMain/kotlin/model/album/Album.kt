package model.album

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import model.Result
import model.artist.Artist
import model.cover.CoverSize
import model.track.Options
import model.track.Track

@Serializable
data class Album(
    val id: Int,
    val title: String,
    val type: AlbumType? = null,
    val metaType: MetaType,
    val year: UInt? = null,
    val releaseDate: Instant? = null,
    val coverUri: String,
    val ogImage: String,
    val genre: GenreType? = null,
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
    val volumes: List<List<Track>>? = null
) : Result() {
    fun getCoverUri(size: CoverSize) = "https://${coverUri.replace("%%", size.toString())}"
    fun getOgImage(size: CoverSize) = "https://${ogImage.replace("%%", size.toString())}"
}
