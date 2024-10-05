package track.model.downloadInfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Container {
    @SerialName("hls")
    HLS
}