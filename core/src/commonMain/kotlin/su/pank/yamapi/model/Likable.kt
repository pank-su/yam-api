package su.pank.yamapi.model

/**
 * Объекты, которые можно лайкнуть
 */
interface Likable {
    /**
     * Лайкнуть трек
     */
    suspend fun like(): Boolean

    /**
     * Убрать лайк
     */
    suspend fun unlike(): Boolean
}