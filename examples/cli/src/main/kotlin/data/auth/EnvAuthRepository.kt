package data.auth

/**
 * Получение токена из env
 */
class EnvAuthRepository: AuthRepository {
    override suspend fun getToken(): String? {
        return System.getenv("YAM_TOKEN")
    }

    override suspend fun setToken(token: String) {
        throw Exception("Нельзя установить токен в env")
    }
}