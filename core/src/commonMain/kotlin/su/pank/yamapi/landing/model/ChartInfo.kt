package su.pank.yamapi.landing.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import su.pank.yamapi.model.Playlist
import su.pank.yamapi.track.model.TrackData

/**
 * @property id идентификатор чарта
 * @property type тип чарта
 * @property typeForFrom откуда получен
 * @property title Название
 * @property chart Список всех треков в виде плейлиста
 *
 * @property chartDescription Описание чарта
 *
 * @see Playlist
 */

@Serializable
data class ChartInfo(
    val id: String,
    val type: String,
    val typeForFrom: String,
    val title: String,
    val menu: ChartInfoMenu,
    val chart: Playlist? = null,
    val chartDescription: String? = null
)


@Serializable
data class Chart(val position: Int, val progress: String, val listeners: Int, val shift: Int)

@Serializable
data class ChartInfoMenu(val items: List<ChartInfoMenuItem>)


@Serializable
data class ChartInfoMenuItem(val title: String, val url: String, val selected: Boolean? = null)


@Serializable
data class ChartItem(val track: TrackData, val chart: Chart)

enum class ChartOption {
    @SerialName("world")
    World,

    @SerialName("russia")
    Russia
}