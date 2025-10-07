package su.pank.yamapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import su.pank.yamapi.exceptions.NotAuthenticatedException
import su.pank.yamapi.exceptions.SessionExpiredException
import su.pank.yamapi.exceptions.ValidateException

/**
 * Типы ошибок.
 */
@Serializable
enum class ErrorType {
    @SerialName("session-expired")
    SessionExpired,

    @SerialName("not-authenticated")
    NotAuthenticated,

    @SerialName("validate")
    Validate,

    ;

    /**
     * Преобразует в исключение.
     *
     * @param message Сообщение.
     * @return Исключение.
     */
    fun toException(message: String): Exception =
        when (this) {
            SessionExpired -> SessionExpiredException(message)
            NotAuthenticated -> NotAuthenticatedException()
            Validate -> ValidateException(message)
        }
}

/**
 * Описание ошибки.
 *
 * @param type Тип ошибки.
 * @param message Сообщение ошибки.
 */
@Serializable
data class Error(
    @SerialName("name") val type: ErrorType,
    val message: String,
)

/**
 * Обрабатывает ошибку, выбрасывая исключение.
 *
 * @param error Ошибка.
 */
fun handleError(error: Error): Unit = throw error.type.toException(error.message)
