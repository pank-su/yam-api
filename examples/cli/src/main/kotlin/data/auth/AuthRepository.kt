package data.auth

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    // Токен авторизации яндекс музыки
    suspend fun getToken(): String?

    suspend fun setToken(token: String)

}