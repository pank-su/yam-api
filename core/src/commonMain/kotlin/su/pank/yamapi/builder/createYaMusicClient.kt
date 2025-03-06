package su.pank.yamapi.builder

import su.pank.yamapi.YamApiClient


fun createYaMusicApiClient(config: YaMusicApiClientBuilder.() -> Unit): YamApiClient {

    return YaMusicApiClientBuilder().apply(config).build()
}