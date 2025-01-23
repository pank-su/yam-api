package su.pank.yamapi.track

import model.cover.CoverSize
import su.pank.yamapi.YaMusicApiClient
import su.pank.yamapi.track.model.TrackData
import track.model.downloadInfo.Codec
import track.model.downloadInfo.DownloadInfo


class Track(
    private val client: YaMusicApiClient,
    val trackData: TrackData,

    ) {

    val id: String get() = trackData.id
    val title: String get() = trackData.title
    val artist get() = trackData.artists // TODO: replace Artist to ArtistData


    fun getUrlOgImage(size: CoverSize) = "https://${trackData.ogImageUri?.replace("%%", size.toString())}"
    fun getUrlCover(size: CoverSize) = "https://${trackData.coverUri?.replace("%%", size.toString())}"

    private suspend fun fetchDownloadInfo(): List<DownloadInfo> {

        return client.tracks.downloadInfo(this.id.toString())
    }

    suspend fun downloadInfo(codec: Codec, bitrateInKbps: Int): DownloadInfo {
        val downloadInfo = this.fetchDownloadInfo()

        return downloadInfo.firstOrNull { it.codec == codec && it.bitrateInKbps == bitrateInKbps }
            ?: downloadInfo.first()
    }
}

