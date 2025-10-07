package data

import data.auth.EnvAuthRepository
import data.auth.FileAuthRepository
import su.pank.yamapi.YamApiClient
import su.pank.yamapi.builder.createYaMusicApiClient

/**
 * Получение [su.pank.yamapi.YamApiClient] из токена
 */
suspend fun yamApiClient(): YamApiClient {
    val envRepository = EnvAuthRepository()
    val envToken = envRepository.getToken()
    val token: String?
    if (envToken == null) {
        val fileRepository = FileAuthRepository()
        token = fileRepository.getToken()
    } else {
        token = envToken
    }
    return createYaMusicApiClient { this.token = token }
}