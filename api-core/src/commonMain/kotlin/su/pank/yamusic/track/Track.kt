package su.pank.yamusic.track

import model.cover.CoverSize
import su.pank.yamusic.YaMusicApiClient
import su.pank.yamusic.exceptions.ExperimentalYaMusicApi
import su.pank.yamusic.track.model.*
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

