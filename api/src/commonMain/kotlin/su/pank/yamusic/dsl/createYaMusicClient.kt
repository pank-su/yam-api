package su.pank.yamusic

Создание клиента с заданными настройками
 */

suspend fun createYaMusicClient(init: YaMusicClient.() -> Unit): YaMusicClient {
    val yaMusicClient = YaMusicClient()
    yaMusicClient.init(init)
    return yaMusicClient
}

// TODO: builder для создания клиента -- сделать создания клиента более настраевым
