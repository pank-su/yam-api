@file:OptIn(ExperimentalSerializationApi::class, ExperimentalTime::class)

package su.pank.yamapi.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames
import su.pank.yamapi.album.model.Album
import su.pank.yamapi.playlist.model.PlaylistData
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
data class Like(
    val type: LikeType,
    val id: String? = null,
    val timestamp: Instant? = null,
    val album: Album? = null,
    val artist: Artist? = null,
    val playlist: PlaylistData? = null,
    @JsonNames("shortDescription", "short_description")
    val shortDescription: String? = null,
    val description: String? = null,
    @JsonNames("isPremiere", "is_premiere")
    val isPremiere: Boolean? = null,
    @JsonNames("isBanner", "is_banner")
    val isBanner: Boolean? = null,
)

@Serializable
enum class LikeType {
    @SerialName("album")
    ALBUM,

    @SerialName("artist")
    ARTIST,

    @SerialName("playlist")
    PLAYLIST,
}
