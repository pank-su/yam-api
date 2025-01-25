package su.pank.yamapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Error
import model.InvocationInfo
import model.handleError


@Serializable
data class BasicResponse<T>(
    val invocationInfo: InvocationInfo,
    @SerialName("result") private val _result: T? = null,
    @SerialName("error") private val _error: Error? = null
) {
    val result: T
        get() {
            if (_result == null) {
                _error?.let {   handleError(_error) } ?: throw Exception()
            }
            return _result!!
        }
}
