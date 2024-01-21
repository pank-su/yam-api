package model.track

import Client
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.album.Album
import model.album.MetaType
import model.artist.Artist
import model.cover.CoverSize
import model.downloadInfo.Codec
import model.downloadInfo.DownloadInfo
import utils.IntOrStringSerializer

@Serializable
data class Track(
    @Serializable(with = IntOrStringSerializer::class)
    val id: Int,
    val title: String,
    val available: Boolean,
    val availableForPremiumUsers: Boolean,
    val availableFullWithoutPermission: Boolean,
    val availableForOptions: List<Options> = listOf(),
    val durationMs: Int? = null,
    val previewDurationMs: Int? = null,
    val storageDir: String? = null,
    val fileSize: Int? = null,
    val r128: R128? = null,
    val artists: List<Artist>,
    val albums: List<Album>,
    val trackSource: String? = null,
    val major: Major? = null,
    @SerialName("ogImage") val ogImageUri: String? = null,
    val coverUri: String? = null,
    val lyricsAvailable: Boolean,
    val lyricsInfo: LyricsInfo? = null,
    val derivedColors: DerivedColors? = null,
    val type: MetaType,
    val rememberPosition: Boolean,
    val trackSharingFlag: TrackSharingFlag? = null,
    val contentWarning: String? = null
) {
    var downloadInfo: List<DownloadInfo>? = null

    fun getUrlOgImage(size: CoverSize) = "https://${ogImageUri?.replace("%%", size.toString())}"
    fun getUrlCover(size: CoverSize) = "https://${coverUri?.replace("%%", size.toString())}"


    suspend fun fetchDownloadInfo(client: Client): List<DownloadInfo>? {
        if (downloadInfo != null) return downloadInfo
        downloadInfo = client.tracksDownloadInfo(this.id).value
        return downloadInfo
    }

    suspend fun specificDownloadInfo(client: Client, codec: Codec, bitrateInKbps: Int): DownloadInfo {
        val downloadInfo = this.fetchDownloadInfo(client)

        return downloadInfo?.firstOrNull { it.codec == codec && it.bitrateInKbps == bitrateInKbps }
            ?: downloadInfo!!.first()
    }

}

