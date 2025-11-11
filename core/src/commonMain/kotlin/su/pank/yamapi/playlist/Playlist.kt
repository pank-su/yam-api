package su.pank.yamapi.playlist

import su.pank.yamapi.YamApiClient
import su.pank.yamapi.model.Likable
import su.pank.yamapi.model.cover.CoverSize
import su.pank.yamapi.model.cover.WithCover
import su.pank.yamapi.playlist.model.PlaylistData
import su.pank.yamapi.playlist.model.TrackShort
import su.pank.yamapi.track.Track
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
class Playlist(
    private val client: YamApiClient,
    playlistData: PlaylistData,
): Likable, WithCover {
    /**
     * Id плейлиста
     */
    val id: String get() = "$ownerId:$kind"
    /**
     * ID владельца плейлиста
     */
    val ownerId: Int = playlistData.uid

    /**
     * ID плейлиста у пользователя
     *
     * Замечание: плейлист "Мне нравится" **всегда** под номером 3
     */
    val kind: Int = playlistData.kind
    val title: String = playlistData.title
    val description: String = playlistData.description ?: ""
    val trackCount: Int = playlistData.trackCount
    val tags: List<String> = playlistData.tags
    val revision: Int = playlistData.revision
    val snapshot: Int = playlistData.snapshot
    val visibility  = playlistData.visibility
    val collective: Boolean = playlistData.collective
    val created = playlistData.created
    val modified = playlistData.modified
    val isBanner: Boolean = playlistData.isBanner
    val isPremiere: Boolean  = playlistData.isPremiere
    val everPlayed: Boolean? = playlistData.everPlayed
    val durationMs: Int? = playlistData.durationMs
    val trackShorts: List<TrackShort> = playlistData.tracks
    private val ogImageUri: String? = playlistData.ogImageUri
    private val backgroundImageUrl: String? = playlistData.backgroundImageUrl


    private var _tracks: List<Track>? = null

    suspend fun tracks(): List<Track> {
        _tracks = _tracks ?: client.tracks(*trackShorts.map { it.id }.toTypedArray())
        requireNotNull(_tracks)
        return _tracks!!
    }

    fun urlOgImage(size: CoverSize) = buildImageUrl(ogImageUri, size)

    fun urlBackgroundImage(size: CoverSize) = buildImageUrl(backgroundImageUrl, size)
    override suspend fun like() =
        client.like<Playlist>(id)


    override suspend fun unlike() =
        client.unlike<Playlist>(id)


    override fun toString(): String {
        return "Playlist(ownerId=$ownerId, kind=$kind, title='$title', description='$description', trackCount=$trackCount, tags=$tags, revision=$revision, snapshot=$snapshot, visibility=$visibility, collective=$collective, created=$created, modified=$modified, isBanner=$isBanner, isPremiere=$isPremiere, everPlayed=$everPlayed, durationMs=$durationMs)"
    }


}