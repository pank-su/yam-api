package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InvocationInfo(
    @SerialName("hostname") val hostName: String,
    @SerialName("req-id") val reqId: String,
    @SerialName("exec-duration-millis") val execMillis: Int = 0
)
