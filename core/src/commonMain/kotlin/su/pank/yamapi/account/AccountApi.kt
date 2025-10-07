package su.pank.yamapi.account

import su.pank.yamapi.YamApiClient
import su.pank.yamapi.account.model.Ad
import su.pank.yamapi.account.model.PermissionAlerts
import su.pank.yamapi.account.model.PromoCodeStatus
import su.pank.yamapi.account.model.Status
import su.pank.yamapi.account.model.UserSettings

class AccountApi(
    private val client: YamApiClient,
) {
    /**
     * Запрос статуса пользователя (/account/status)
     *
     * @see Status
     */
    suspend fun status() = client.get<Status>("account", "status")

    /**
     * Запрос настройка пользователя (/account/settings)
     *
     * @see UserSettings
     */
    suspend fun settings() = client.get<UserSettings>("account", "settings")

    /**
     * Запрос рекламы пользователя (/settings)
     *
     * @see Ad
     */
    suspend fun ads() = client.get<Ad>("settings")

    suspend fun permissionAlerts() = client.get<PermissionAlerts>("permission-alerts")

    /**
     * Запрос экспериментов для данного аккаунта (/account/experiments)
     *
     * @return словарь всех экспериментов
     */
    suspend fun experiments() = client.get<HashMap<String, String>>("account", "experiments")

    /**
     * Активация промокода (/account/consume-promo-code)
     *
     * @param code промокод
     *
     * @see PromoCodeStatus
     */
    suspend fun consumePromoCode(code: String): PromoCodeStatus =
        client.form(
            hashMapOf(
                "code" to code,
                "language" to client.language.toString(),
            ),
            "account",
            "consume-promo-code",
        )
}
