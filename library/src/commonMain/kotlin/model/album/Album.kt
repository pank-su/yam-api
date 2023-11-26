package model.album

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.artist.Artist
import model.track.Options

@Serializable
enum class AlbumType {
    @SerialName("single")
    Single,

    @SerialName("compilation")
    Compilation
}

@Serializable
enum class MetaType {
    @SerialName("music")
    Music
}

@Serializable
enum class Genre {
    @SerialName("rock")
    Rock,

    @SerialName("alternative")
    Alternative,

    @SerialName("pop")
    Pop,

    @SerialName("films")
    Films,

    @SerialName("rusrap")
    RusRap,

    @SerialName("local-indie")
    LocalIndie,

    @SerialName("indie")
    Indie,

    @SerialName("rap")
    Rap,

    @SerialName("foreignrap")
    ForeignRap,

    @SerialName("ska")
    SKA,

    @SerialName("punk")
    Punk,

    @SerialName("postpunk")
    PostPunk,

    @SerialName("rusrock")
    RusRock,

    @SerialName("animated")
    Animated,

    @SerialName("soundtrack")
    SoundTrack,

    @SerialName("rusbards")
    Rusbards
}

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
    val genre: Genre,
    val trackCount: Int,
    val likesCount: Int,
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
    val trackPosition: TrackPosition
)
