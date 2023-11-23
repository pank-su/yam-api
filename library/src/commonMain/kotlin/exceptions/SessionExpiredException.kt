package exceptions

class SessionExpiredException(override val message: String): Exception(message)