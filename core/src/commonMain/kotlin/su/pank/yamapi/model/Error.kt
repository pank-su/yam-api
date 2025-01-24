package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import su.pank.yamapi.exceptions.NotAuthenticatedException
import su.pank.yamapi.exceptions.SessionExpiredException
import su.pank.yamapi.exceptions.ValidateException


@Serializable
enum class ErrorType {
    @SerialName("session-expired")
    SessionExpired,

    @SerialName("not-authenticated")
    NotAuthenticated,

    @SerialName("validate")
    Validate;

    fun toException(message: String): Exception = when (this) {
        SessionExpired   -> SessionExpiredException(message)
        NotAuthenticated -> NotAuthenticatedException()
        Validate         -> ValidateException(message)
    }

}



@Serializable
data class Error(@SerialName("name") val type: ErrorType, val message: String)

fun handleError(error: Error){
    throw error.type.toException(error.message)
}
