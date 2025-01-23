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
    Validate
}

val errorTypeToException: HashMap<ErrorType?, (String) -> Unit> = hashMapOf(
    ErrorType.SessionExpired to { throw SessionExpiredException(it) },
    ErrorType.NotAuthenticated to { throw NotAuthenticatedException() },
    ErrorType.Validate to { throw ValidateException(it) },
    null to { throw SessionExpiredException(it) }
)


@Serializable
data class Error(@SerialName("name") val type: ErrorType, val message: String)
