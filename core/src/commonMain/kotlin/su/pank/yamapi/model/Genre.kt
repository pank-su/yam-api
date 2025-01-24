package su.pank.yamapi.model

import kotlinx.serialization.Serializable
import model.Icon

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
    val hideInRegions: List<Int>? = null
)


@Serializable
data class GenreImages(val `208x208`: String? = null, val `300x300`: String? = null)

@Serializable
data class GenreTitle(val title: String, val fullTitle: String? = null)
