package su.pank.yamusic.account

import su.pank.yamusic.YaMusicApiClient
import model.PermissionAlerts
import su.pank.yamusic.account.model.PromoCodeStatus
import su.pank.yamusic.account.model.Status
import su.pank.yamusic.account.model.UserSettings
import model.ad.Ad

class AccountApi(private val client: YaMusicApiClient) {
    /**
     * Запрос статуса пользователя (/account/status)
     *
     * @see Status
     */
    suspend fun status() = client.request<Status>("account", "status")

    /**
     * Запрос настройка пользователя (/account/settings)
     *
     * @see UserSettings
     */
    suspend fun settings() = client.request<UserSettings>("account", "settings")

    /**
     * Запрос рекламы пользователя (/settings)
     *
     * @see Ad
     */
    suspend fun ads() = client.request<Ad>("settings")


    suspend fun permissionAlerts() = client.request<PermissionAlerts>("permission-alerts")

    /**
     * Запрос экспериментов для данного аккаунта
     *
     * @return словарь всех экспериментов
     */
    suspend fun experiments() = client.request<HashMap<String, String>>("account", "experiments")

    /**
     * Активация промокода
     *
     * @param code промокод
     *
     * @see PromoCodeStatus
     */
    suspend fun consumePromoCode(code: String) = client.requestForm<PromoCodeStatus>(
        "account",
        "consume-promo-code",
        body = hashMapOf("code" to code, "language" to client.language.toString())
    )
}