package su.pank.yamapi.model.album

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
    AudioBook,

    @SerialName("fairy-tale")
    FairyTale,

    @SerialName("music")
    Music,

    @SerialName("asmr")
    ASMR
}