package model

import Client
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
open class Result {
    @Transient var invocationInfo: InvocationInfo? = null
    @Transient var client: Client? = null
}



@Serializable
data class BasicResponse<T>(
    val invocationInfo: InvocationInfo,
    @SerialName("result") private val _result: T? = null,
    @SerialName("error") private val _error: Error? = null
) where T : Result {
    val result: T
        get() {
            if (_result == null) {
                errorTypeToException[_error?.type]!!(_error?.message ?: "Неизвестна ошибка")
                throw Throwable()
            }
            return _result.apply {
                this.invocationInfo = this@BasicResponse.invocationInfo
            }
        }
}