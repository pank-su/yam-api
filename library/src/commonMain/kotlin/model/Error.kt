package model

import exceptions.NotAuthenticatedException
import exceptions.SessionExpiredException
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
enum class ErrorType{
    @SerialName("session-expired")
    SessionExpired,
    @SerialName("not-authenticated")
    NotAuthenticated
}

val errorTypeToException: HashMap<ErrorType?, (String) -> Unit> = hashMapOf(
    ErrorType.SessionExpired to {throw SessionExpiredException(it) },
    ErrorType.NotAuthenticated to { throw NotAuthenticatedException() },
    null to {throw SessionExpiredException(it) }
)


@Serializable
data class Error(@SerialName("name") val type: ErrorType, val message: String)
