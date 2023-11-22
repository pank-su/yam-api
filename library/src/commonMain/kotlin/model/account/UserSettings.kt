package model.account

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Result

enum class Visibility {
    PUBLIC, PRIVATE
}

@Serializable
enum class Theme {
    @SerialName("light")
    Light,

    @SerialName("black")
    Black
}

@Serializable
data class UserSettings(
    val uid: Int?,
    val lastFmScrobblingEnabled: Boolean,
    val facebookScrobblingEnabled: Boolean,
    val shuffleEnabled: Boolean,
    val addNewTrackOnPlaylistTop: Boolean,
    val volumePercents: Int,
    val userMusicVisibility: Visibility,
    val userSocialVisibility: Visibility,
    val modified: Instant? = null,
    val theme: Theme,
    val promosDisabled: Boolean,
    val autoPlayRadio: Boolean,
    val syncQueueEnabled: Boolean,
    val childModEnabled: Boolean?,
    val adsDisabled: Boolean?
) : Result()
