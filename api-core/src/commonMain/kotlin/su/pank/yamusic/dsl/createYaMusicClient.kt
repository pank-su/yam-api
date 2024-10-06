package su.pank.yamusic

import su.pank.yamusic.builder.YaMusicApiClientBuilder


suspend fun createYaMusicApiClient(config: YaMusicApiClientBuilder.() -> Unit): YaMusicApiClient {

    return YaMusicApiClientBuilder().apply(config).build()
}

// TODO: builder для создания клиента -- сделать создания клиента более настраевым
