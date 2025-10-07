package su.pank.yamapi.exceptions

/**
 * Исключение, выбрасываемое при истечении сессии.
 *
 * @param message Сообщение.
 */
class SessionExpiredException(
    override val message: String,
) : Exception(message)
