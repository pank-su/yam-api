package landing.model.feed

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

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