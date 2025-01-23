package su.pank.yamapi.track.model.supplement

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.cover.CoverSize

@Serializable
data class VideoSupplement(
    val cover: String,
    val provider: VideoProvider,
    val title: String? = null,
    val providerVideoId: String? = null,
    val url: String? = null,
    val embedUrl: String? = null,
    val embed: String? = null
) {
    fun getCover(size: CoverSize) = "https://${cover.replace("%%", size.toString())}"

}

// TODO
@Serializable
enum class VideoProvider {
    @SerialName("yandex")
    Yandex
}
