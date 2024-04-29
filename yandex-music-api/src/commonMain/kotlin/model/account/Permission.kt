package model.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Все возможные права пользователя
 *
 * @see Permissions
 */
@Serializable
enum class Permission{
    @SerialName("landing-play") LandingPlay,
    @SerialName("feed-play") FeedPlay,
    @SerialName("radio-play") RadioPlay,
    @SerialName("mix-play") MixPlay,
    @SerialName("radio-skips") RadioSkips,
    @SerialName("library-cache") LibraryCache,
    @SerialName("library-play") LibraryPlay,
    @SerialName("high-quality") HighQuality,
    @SerialName("ads-skips") AdsSkips,
    @SerialName("non-shuffled-play") NonShuffledPlay,
    @SerialName("background-play") BackgroundPlay,
    @SerialName("play-premium-tracks") PlayPremiumTracks,
    @SerialName("auto-flow") AutoFlow,
    @SerialName("play-full-tracks") PlayFullTracks,
    @SerialName("play-radio-full-tracks") PlayRadioFullTracks
}