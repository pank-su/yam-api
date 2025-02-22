package su.pank.yamapi.landing

import kotlinx.serialization.json.Json
import su.pank.yamapi.YaMusicApiClient
import su.pank.yamapi.exceptions.NotAuthenticatedException
import su.pank.yamapi.landing.model.*
import su.pank.yamapi.utils.removeCarets

class LandingApi(private val client: YaMusicApiClient) {
    /**
     * Получение сгенерированных плейлистов
     *
     * @see Feed
     */
    @Deprecated("Лучше использовать landing функции", level = DeprecationLevel.WARNING)
    suspend fun feed() = client.request<Feed>("feed")

    /**
     * Получение landing'а по различным блокам. Для этого также можно использовать отдельные запросы.
     *
     * @param blocks получаемые блоки, в зависимости от выбранных блоков, будет разный контент
     *
     * @see Landing
     * @see BlockType
     */
    suspend operator fun invoke(vararg blocks: BlockType = BlockType.entries.toTypedArray()): Landing =
        getBlocks(*blocks)


    /**
     * Получение landing'а по различным блокам. Для этого также можно использовать отдельные запросы.
     *
     * @param blocks получаемые блоки, в зависимости от выбранных блоков, будет разный контент
     *
     * @see Landing
     * @see BlockType
     */
    suspend fun getBlocks(vararg blocks: BlockType) =
        client.request<Landing>(
            listOf("landing3"),
            body = hashMapOf(
                "blocks" to blocks.joinToString(",") {
                    Json.encodeToString(it).removeCarets()

                },
                "eitherUserId" to (client.me?.account?.uid ?: throw NotAuthenticatedException()).toString()
            )
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
        client.request<ChartInfo>(
            "landing3", "chart", if (chartOption != null) {
                Json.encodeToString(chartOption).removeCarets()
            } else ""
        )

    /**
     * Получение новых релизов
     *
     * @see LandingList
     */
    suspend fun newReleases() = client.request<LandingList>("landing3", "new-releases")

    /**
     * Получение новых плейлистов
     *
     * @see LandingList
     */
    suspend fun newPlaylists() = client.request<LandingList>("landing3", "new-playlists")

    /**
     * Получение рекомендации подкастов
     *
     * @see LandingList
     */
    suspend fun podcasts() = client.request<LandingList>("landing3", "podcasts")

    /**
     * WTF?
     */
    suspend fun feedWizardIsPassed() =
        client.request<HashMap<String, Boolean>>("feed", "wizard", "is-passed")["isWizardPassed"] as Boolean

}