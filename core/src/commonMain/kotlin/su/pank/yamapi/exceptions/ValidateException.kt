package su.pank.yamapi.exceptions

/**
 * Исключение валидации.
 *
 * @param message Сообщение.
 */
class ValidateException(
    override val message: String,
) : Exception()
