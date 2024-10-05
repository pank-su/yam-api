package su.pank.yamusic.landing.model.feed

import kotlinx.datetime.LocalDate
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames
import model.playlist.Playlist
import su.pank.yamusic.track.model.TrackDTO


@Serializable
data class Feed(
    val pumpkin: Boolean,
    val canGetMoreEvents: Boolean,
    val isWizardPassed: Boolean,
    val generatedPlaylists: List<GeneratedPlaylist>,
    val today: LocalDate,
    val days: List<Day>
)

@Serializable
data class GeneratedPlaylist(
    val type: GeneratedPlaylistType,
    val ready: Boolean,
    val notify: Boolean,
    val data: Playlist
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
enum class GeneratedPlaylistType {
    @JsonNames("playlist_of_the_day", "playlistOfTheDay")
    PlaylistOfTheDay,

    origin,

    @JsonNames("recent_tracks", "recentTracks")
    RecentTracks,

    @JsonNames("never_heard", "neverHeard")
    NeverHeard,

    podcasts,

    @JsonNames("missed_likes", "missedLikes")
    MissedLikes,

    rewind2023
}

@Serializable
data class Day(val day: LocalDate, val events: List<Event>)

// TODO: добавить преобразование в нормальные треки
@Serializable
data class Event(
    val id: String,
    val type: EventType,
    val typeForFrom: String? = null,
    val title: String? = null,
    val tracks: List<TrackDTO>? = null
)

@Serializable
enum class EventType {
    @SerialName("type")
    Type,

    @SerialName("tracks")
    Tracks,

    @SerialName("artists")
    Artists,

    @SerialName("albums")
    Albums,

    @SerialName("notification")
    Notification,

    @SerialName("social-tracks")
    SocialTracks
}