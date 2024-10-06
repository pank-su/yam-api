package su.pank.yamusic.account.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Права пользователя
 *
 * @param until до какого времени действуют права
 * @param values права
 * @param default права по умолчанию (права пользователя без подписки)
 *
 * @see Permission
 */

@Serializable
data class Permissions(val until: Instant, val values: Set<Permission>, val default: Set<Permission>)

/**
 * Все возможные права пользователя
 *
 * @see Permissions
 */
@Serializable
enum class Permission {
    @SerialName("landing-play")
    LandingPlay,

    @SerialName("feed-play")
    FeedPlay,

    @SerialName("radio-play")
    RadioPlay,

    @SerialName("mix-play")
    MixPlay,

    @SerialName("radio-skips")
    RadioSkips,

    @SerialName("library-cache")
    LibraryCache,

    @SerialName("library-play")
    LibraryPlay,

    @SerialName("high-quality")
    HighQuality,

    @SerialName("ads-skips")
    AdsSkips,

    @SerialName("non-shuffled-play")
    NonShuffledPlay,

    @SerialName("background-play")
    BackgroundPlay,

    @SerialName("play-premium-tracks")
    PlayPremiumTracks,

    @SerialName("auto-flow")
    AutoFlow,

    @SerialName("play-full-tracks")
    PlayFullTracks,

    @SerialName("play-radio-full-tracks")
    PlayRadioFullTracks
}