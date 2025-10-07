package data.auth

import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.exists
import kotlin.io.path.notExists

/**
 * Получение токена из ~/.config/yamcl/token
 */
class FileAuthRepository: AuthRepository {
    private val home = System.getProperty("user.home")
    private val path = Paths.get(home, ".config", "yamcl", "token")
    override suspend fun getToken(): String? {
        if (path.notExists()) return null
        return Files.readString(path)
    }

    override suspend fun setToken(token: String) {
        Files.createDirectories(path)
        Files.writeString(path, token)
    }
}