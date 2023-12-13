package model.album

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class AlbumType {
    @SerialName("single")
    Single,

    @SerialName("compilation")
    Compilation,

    @SerialName("podcast")
    Podcast,

    @SerialName("audiobook")
    AudioBook
}