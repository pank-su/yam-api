package landing.model.chart

import kotlinx.serialization.Serializable
import model.playlist.Playlist

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
