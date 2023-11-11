package model

import kotlinx.serialization.Serializable

@Serializable
data class InvocationInfo(val hostName: String, val reqId: String, val execDuration: Int)
