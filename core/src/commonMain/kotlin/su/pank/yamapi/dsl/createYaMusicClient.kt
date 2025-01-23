package su.pank.yamapi

import su.pank.yamapi.builder.YaMusicApiClientBuilder


fun createYaMusicApiClient(config: YaMusicApiClientBuilder.() -> Unit): YaMusicApiClient {

    return YaMusicApiClientBuilder().apply(config).build()
}

// TODO: builder для создания клиента -- сделать создания клиента более настраевым
