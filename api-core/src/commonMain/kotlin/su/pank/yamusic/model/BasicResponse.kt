package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class BasicResponse<T>(
    val invocationInfo: InvocationInfo,
    @SerialName("result") private val _result: T? = null,
    @SerialName("error") private val _error: Error? = null
) {
    val result: T
        get() {
            if (_result == null) {
                errorTypeToException[_error?.type]!!(_error?.message ?: "Неизвестна ошибка")
                throw Throwable()
            }
            return _result
        }
}
