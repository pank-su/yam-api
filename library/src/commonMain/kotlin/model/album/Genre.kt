package model.album

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    Rusbards,

    @SerialName("ruspop")
    RusPop,

    @SerialName("dance")
    Dance,

    @SerialName("podcasts")
    Podcasts,

    // TODO
    edmgenre, breakbeatgenre, ukrrock, experimental, electronics, industrial,

    @SerialName("social-tracks")
    SocialTracks,

    @SerialName("dnb")
    DrumAndBase,

    @SerialName("soviet")
    Soviet,

    @SerialName("fairytales")
    FairyTales
}