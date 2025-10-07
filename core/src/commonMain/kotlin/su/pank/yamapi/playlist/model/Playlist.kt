@file:OptIn(ExperimentalTime::class)

package su.pank.yamapi.playlist.model

import su.pank.yamapi.YamApiClient
import su.pank.yamapi.model.cover.CoverSize
import su.pank.yamapi.track.Track
import kotlin.time.ExperimentalTime

class Playlist(
    private val client: YamApiClient,
    val playlistData: PlaylistData,
) {
    val uid: Int get() = playlistData.uid
    val kind: Int get() = playlistData.kind
    val title: String get() = playlistData.title
    val description: String get() = playlistData.description ?: ""
    val trackCount: Int get() = playlistData.trackCount
    val tags: List<String> get() = playlistData.tags
    val revision: Int get() = playlistData.revision
    val snapshot: Int get() = playlistData.snapshot
    val visibility get() = playlistData.visibility
    val collective: Boolean get() = playlistData.collective
    val created get() = playlistData.created
    val modified get() = playlistData.modified
    val isBanner: Boolean get() = playlistData.isBanner
    val isPremiere: Boolean get() = playlistData.isPremiere
    val everPlayed: Boolean? get() = playlistData.everPlayed
    val durationMs: Int? get() = playlistData.durationMs
    val trackShorts: List<TrackShort> get() = playlistData.tracks

    private var _tracks: List<Track>? = null

    suspend fun tracks(): List<Track> {
        _tracks = _tracks ?: client.tracks(*trackShorts.map { it.id }.toTypedArray())
        requireNotNull(_tracks)
        return _tracks!!
    }

    fun getUrlOgImage(size: CoverSize) = "https://${playlistData.ogImageUri?.replace("%%", size.toString())}"

    fun getUrlBackgroundImage(size: CoverSize) = "https://${playlistData.backgroundImageUrl?.replace("%%", size.toString())}"
}
