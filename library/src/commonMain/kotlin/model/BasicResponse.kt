package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class Result {
    var invocationInfo: InvocationInfo? = null
}

@Serializable
data class BasicResponse<T>(
    val invocationInfo: InvocationInfo,
    @SerialName("result") private val _result: T?

) where T : Result {
    val result
        get() = _result.apply { this?.invocationInfo = this@BasicResponse.invocationInfo }
}