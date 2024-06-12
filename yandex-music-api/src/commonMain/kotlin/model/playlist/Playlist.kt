package model.playlist

import Client
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import account.model.User
import account.model.Visibility
import model.cover.Cover
import model.cover.CoverSize
import landing.model.feed.GeneratedPlaylistType
import track.model.Track


@Serializable
data class Playlist(
    val owner: User? = null,
    val cover: Cover? = null,
    val coverWithoutText: Cover? = null,
    val madeFor: MadeFor? = null,
    val playCounter: PlayCounter? = null,
    val idForFrom: GeneratedPlaylistType? = null,
    val urlPart: String? = null,
    val descriptionFormatted: String? = null,
    val backgroundVideoUrl: String? = null,
    val backgroundImageUrl: String? = null,
    val uid: Int,
    val kind: Int,
    val title: String,
    val description: String? = null,
    val trackCount: Int,
    val tags: List<String> = listOf(),
    val revision: Int,
    val snapshot: Int,
    val visibility: Visibility,
    val collective: Boolean,
    val created: Instant,
    val modified: Instant,
    val isBanner: Boolean,
    val isPremiere: Boolean,
    val everPlayed: Boolean? = null,
    val durationMs: Int? = null,
    @SerialName("ogImage") val ogImageUri: String? = null,
    val tracks: List<TrackShort> = listOf()
) {
    private var fullTracks: List<Track>? = null

    suspend fun fetchTracks(client: Client): List<Track>? {
        fullTracks = fullTracks ?: client.tracks(*tracks.map { it.id }.toTypedArray())
        return fullTracks
    }

    fun getUrlOgImage(size: CoverSize) = "https://${ogImageUri?.replace("%%", size.toString())}"
    fun getUrlBackgroundImage(size: CoverSize) = "https://${backgroundImageUrl?.replace("%%", size.toString())}"
}
