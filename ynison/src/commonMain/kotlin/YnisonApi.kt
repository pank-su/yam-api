package su.pank.yamapi.ynison

import com.yandex.media.ynison.service.*
import io.github.timortel.kmpgrpc.core.metadata.Key
import io.github.timortel.kmpgrpc.core.metadata.Metadata
import io.ktor.http.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import su.pank.yamapi.YamApiClient
import su.pank.yamapi.exceptions.ExperimentalYamApi
import kotlin.time.Duration.Companion.minutes
import io.github.timortel.kmpgrpc.core.Channel as GRPCChannel

class YnisonApi(private val client: YamApiClient) {

    var deviceId: String = "yam-api"

    var isConnected: Boolean = false
        private set

    private val headers: Map<String, String>
        get() {
            val headers = mutableMapOf<String, String>()
            headers["ynison-device-id"] = deviceId
            headers[HttpHeaders.Authorization] = "OAuth ${client.token}"
            return headers
        }

    internal suspend fun getTicket(): RedirectResponse {
        val redirectChannel = GRPCChannel.Builder.forAddress("ynison.music.yandex.net", 443)
            .build()

        val stub = YnisonRedirectServiceStub(redirectChannel)
        val metadata = headers.toMetadata()

        try {
            return stub.GetRedirectToYnison(RedirectRequest(), metadata)
        } finally {
            redirectChannel.shutdown()
        }

    }


    suspend fun connect() {
        if (isConnected) return

        val ticketResponse = getTicket()

        val headers = headers + ("ynison-redirect-ticket" to ticketResponse.redirect_ticket)

        val grpcChannel = GRPCChannel.Builder.forAddress(ticketResponse.host, 443)
            .build()
        val metadata = headers.toMetadata()
        val ynisonStateService = YnisonStateServiceStub(grpcChannel)
        ynisonStateService.PutYnisonState(flow {

            emit(
                PutYnisonStateRequest(
                    parameters = PutYnisonStateRequest.Parameters.Update_full_state(
                        UpdateFullState(
                            player_state = PlayerState(
                                status = PlayingStatus(paused = true, playback_speed = 1.0),
                                player_queue = playerQueue {
                                    current_playable_index = -1
                                    entity_type = PlayerQueue.EntityType.VARIOUS
                                    options = playerStateOptions {
                                        repeat_mode = PlayerStateOptions.RepeatMode.NONE
                                    }
                                    entity_context = PlayerQueue.EntityContext.BASED_ON_ENTITY_BY_DEFAULT
                                }), device = UpdateDevice(
                                info = DeviceInfo(
                                    deviceId, "yam-api",
                                    DeviceType.WEB, "yam-api"
                                ), capabilities = DeviceCapabilities(false, true)
                            )
                        )
                    )
                )
            )
            delay(1.minutes)
        }, metadata).collect {
            println(it)
        }

    }

    private fun Map<String, String>.toMetadata(): Metadata {
        return this.entries.fold(Metadata.empty()) { acc, entry ->
            acc.withEntry(Key.AsciiKey(entry.key), entry.value)
        }
    }
}


@ExperimentalYamApi
val YamApiClient.ynison: YnisonApi get() = YnisonApi(this)

// TODO: сделать в builder настройку ynisonApi { }