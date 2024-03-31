package model.downloadInfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Codec {
    @SerialName("mp3")
    MP3,

    @SerialName("aac")
    AAC,

    @SerialName("flac")
    FLAC
}