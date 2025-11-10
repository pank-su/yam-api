package su.pank.yamapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Operation {
    @SerialName("insert")
    INSERT,

    @SerialName("delete")
    DELETE
}

data class Difference(@SerialName("op") val operation: Operation, val from: Int, val to: Int)