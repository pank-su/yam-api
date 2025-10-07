package su.pank.yamapi.model

import kotlinx.serialization.Serializable

/**
 * Жанр музыки.
 *
 * @param id Идентификатор жанра.
 * @param weight Вес.
 * @param composerTop Топ композиторов.
 * @param title Заголовок.
 * @param titles Заголовки на разных языках.
 * @param images Изображения.
 * @param showInMenu Показывать в меню.
 * @param showInRegions Регионы, где показывать.
 * @param fullTitle Полный заголовок.
 * @param urlPart Часть URL.
 * @param color Цвет.
 * @param radioIcon Иконка радио.
 * @param subGenres Поджанры.
 * @param hideInRegions Регионы, где скрывать.
 */
@Serializable
data class Genre(
    val id: String,
    val weight: Int,
    val composerTop: Boolean,
    val title: String,
    val titles: HashMap<String, GenreTitle>,
    val images: GenreImages,
    val showInMenu: Boolean,
    val showInRegions: List<Int>? = null,
    val fullTitle: String? = null,
    val urlPart: String? = null,
    val color: String? = null,
    val radioIcon: Icon? = null,
    val subGenres: List<Genre>? = null,
    val hideInRegions: List<Int>? = null,
)

/**
 * Изображения жанра.
 *
 * @param `208x208` Изображение 208x208.
 * @param `300x300` Изображение 300x300.
 */
@Serializable
data class GenreImages(
    val `208x208`: String? = null,
    val `300x300`: String? = null,
)

/**
 * Заголовок жанра.
 *
 * @param title Заголовок.
 * @param fullTitle Полный заголовок.
 */
@Serializable
data class GenreTitle(
    val title: String,
    val fullTitle: String? = null,
)
