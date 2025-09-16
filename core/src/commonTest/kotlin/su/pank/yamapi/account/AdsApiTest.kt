
package su.pank.yamapi.account

import kotlinx.coroutines.test.runTest
import kotlinx.datetime.DateTimePeriod
import su.pank.yamapi.createMockedYamApiClient
import su.pank.yamapi.mockJsonResponse
import su.pank.yamapi.model.ad.*
import su.pank.yamapi.testJson
import su.pank.yamapi.wrapWithBasicResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class AdsApiTest {
    @Test
    fun testAds() = runTest {
        val expectedAd = Ad(
            offersBatchId = "test-batch-id",
            inAppProducts = listOf(
                AppProduct(
                    productId = "test-product-id",
                    offersPositionId = "test-position-id",
                    type = TypeProduct.Subscription,
                    commonPeriodDuration = DateTimePeriod(months = 1),
                    duration = 30,
                    trialDuration = 7,
                    price = Price(199.0f, Currency.RUB),
                    plus = true,
                    feature = "basic-plus",
                    features = listOf(Feature.BasicPlus, Feature.BasicMusic),
                    description = "Test description",
                    paymentMethodTypes = listOf(PaymentMethod.Card),
                    available = true
                )
            ),
            nativeProducts = emptyList(),
            webPaymentUrl = "https://music.yandex.ru/payment",
            promoCodesEnabled = true
        )

        val responseJson = wrapWithBasicResponse(testJson.encodeToString(expectedAd))

        val yamApiClient = createMockedYamApiClient(mockJsonResponse(responseJson))
        val accountApi = AccountApi(yamApiClient)

        val actualAd = accountApi.ads()

        assertEquals(expectedAd, actualAd)
    }
}
