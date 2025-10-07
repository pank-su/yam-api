package su.pank.yamapi.builder

import su.pank.yamapi.YamApiClient

/**
 * Создает клиент YamApiClient с использованием конфигурации.
 *
 * @param config Конфигурация билдера.
 * @return YamApiClient
 */
fun createYaMusicApiClient(config: YaMusicApiClientBuilder.() -> Unit): YamApiClient = YaMusicApiClientBuilder().apply(config).build()
