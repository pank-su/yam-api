package su.pank.yamapi.landing

import kotlinx.serialization.json.Json
import su.pank.yamapi.YamApiClient
import su.pank.yamapi.exceptions.NotAuthenticatedException
import su.pank.yamapi.landing.model.*
import su.pank.yamapi.utils.removeCarets

/**
 * API для работы с лендингом.
 *
 * @param client Клиент YamApiClient.
 */
class LandingApi(
    private val client: YamApiClient,
) {
    /**
     * Получение сгенерированных плейлистов
     *
     * @see Feed
     */
    @Deprecated("Лучше использовать landing функции", level = DeprecationLevel.WARNING)
    suspend fun feed() = client.get<Feed>("feed")

    /**
     * Получение landing'а по различным блокам. Для этого также можно использовать отдельные запросы.
     *
     * @param blocks получаемые блоки, в зависимости от выбранных блоков, будет разный контент
     *
     * @see Landing
     * @see BlockType
     */
    suspend operator fun invoke(vararg blocks: BlockType = BlockType.entries.toTypedArray()): Landing = blocks(*blocks)

    /**
     * Получение landing'а по различным блокам. Для этого также можно использовать отдельные запросы.
     *
     * @param blocks получаемые блоки, в зависимости от выбранных блоков, будет разный контент
     *
     * @see Landing
     * @see BlockType
     */
    suspend fun blocks(vararg blocks: BlockType): Landing =
        client.get(
            hashMapOf(
                "blocks" to
                    blocks.joinToString(",") {
                        Json.encodeToString(it).removeCarets()
                    },
                "eitherUserId" to (null ?: throw NotAuthenticatedException()).toString(), // FIXME
            ),
            "landing3",
        )

    /**
     * Получение чарта мирового или российского
     *
     * @param chartOption выбранный чарт
     *
     * @see ChartOption
     * @see ChartInfo
     */
    suspend fun chart(chartOption: ChartOption? = null) =
        client.get<ChartInfo>(
            "landing3",
            "chart",
            if (chartOption != null) {
                Json.encodeToString(chartOption).removeCarets()
            } else {
                ""
            },
        )

    /**
     * Получение новых релизов
     *
     * @see LandingList
     */
    suspend fun newReleases() = client.get<LandingList>("landing3", "new-releases")

    /**
     * Получение новых плейлистов
     *
     * @see LandingList
     */
    suspend fun newPlaylists() = client.get<LandingList>("landing3", "new-playlists")

    /**
     * Получение рекомендации подкастов
     *
     * @see LandingList
     */
    suspend fun podcasts() = client.get<LandingList>("landing3", "podcasts")

    /**
     * WTF?
     */
    suspend fun feedWizardIsPassed() = client.get<HashMap<String, Boolean>>("feed", "wizard", "is-passed")["isWizardPassed"] as Boolean
}
