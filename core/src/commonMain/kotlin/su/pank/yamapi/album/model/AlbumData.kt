@file:OptIn(ExperimentalTime::class)

package su.pank.yamapi.album.model

import kotlinx.serialization.Serializable
import su.pank.yamapi.model.Artist
import su.pank.yamapi.track.model.Options
import su.pank.yamapi.track.model.TrackData
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
data class AlbumData(
    val id: Int,
    val title: String,
    val type: AlbumType? = null,
    val metaType: AlbumType,
    val year: UInt? = null,
    val releaseDate: Instant? = null,
    val coverUri: String? = null,
    val ogImage: String,
    val genre: String? = null,
    val trackCount: Int,
    val likesCount: Int? = null,
    val recent: Boolean,
    val veryImportant: Boolean,
    val artists: List<Artist>,
    val labels: List<Label>,
    val available: Boolean,
    val availableForPremiumUsers: Boolean,
    val availableForOptions: List<Options>,
    val availableForMobile: Boolean,
    val availablePartially: Boolean,
    val bests: List<Int>,
    val trackPosition: TrackPosition? = null,
    val duplicates: List<AlbumData>? = null,
    val volumes: List<List<TrackData>>? = null,
)

@Serializable(with = LabelSerializer::class)
data class Label(
    val id: Int,
    val name: String,
)

/**
 * Позиция трека в альбоме
 *
 * @see AlbumData
 */
@Serializable
data class TrackPosition(
    val volume: Int,
    val index: Int,
)
