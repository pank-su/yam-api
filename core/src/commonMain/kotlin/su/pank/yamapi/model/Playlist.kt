package model.playlist

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.cover.Cover
import model.cover.CoverSize
import su.pank.yamapi.YaMusicApiClient
import su.pank.yamapi.account.model.User
import su.pank.yamapi.account.model.Visibility
import su.pank.yamapi.landing.model.GeneratedPlaylistType
import su.pank.yamapi.track.model.TrackData
import su.pank.yamapi.utils.IntOrStringSerializer


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
    private var fullTracks: List<TrackData>? = null

    suspend fun fetchTracks(client: YaMusicApiClient): List<TrackData>? {
        fullTracks = fullTracks ?: client.tracks(*tracks.map { it.id }.toTypedArray())
        return fullTracks
    }

    fun getUrlOgImage(size: CoverSize) = "https://${ogImageUri?.replace("%%", size.toString())}"
    fun getUrlBackgroundImage(size: CoverSize) = "https://${backgroundImageUrl?.replace("%%", size.toString())}"
}


@Serializable
data class PlayCounter(val value: UInt, val description: String, val updated: Boolean)

@Serializable
data class MadeFor(val userInfo: User, val caseForms: CaseForms)


@Serializable
data class CaseForms(
    @SerialName("accusative")
    val accusative: String,
    @SerialName("dative")
    val dative: String,
    @SerialName("genitive")
    val genitive: String,
    @SerialName("instrumental")
    val instrumental: String,
    @SerialName("nominative")
    val nominative: String,
    @SerialName("prepositional")
    val prepositional: String
)

@Serializable
data class PlaylistId(val uid: Int, val kind: Int)

@Serializable
data class TagResult(val tag: Tag, val ids: List<PlaylistId>)

@Serializable
data class Tag(val id: String, val value: String, val name: String, val ogDescription: String)


@Serializable
data class TrackShort(
    @Serializable(with = IntOrStringSerializer::class)
    val id: String,
    val timestamp: Instant
) {
    var track: TrackData? = null
    suspend fun fetchTrack(client: YaMusicApiClient): TrackData? {
        track = client.tracks(id)[0]
        return track
    }
}
