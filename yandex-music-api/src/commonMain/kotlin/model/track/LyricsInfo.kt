package model.track


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LyricsInfo(
    @SerialName("hasAvailableSyncLyrics")
    val hasAvailableSyncLyrics: Boolean,
    @SerialName("hasAvailableTextLyrics")
    val hasAvailableTextLyrics: Boolean
)