package model.genre

import kotlinx.serialization.Serializable
import model.Icon

@Serializable
data class Genre(
    val id: String,
    val weight: Int,
    val composerTop: Boolean,
    val title: String,
    val titles: HashMap<String, Title>,
    val images: Images,
    val showInMenu: Boolean,
    val showInRegions: List<Int>? = null,
    val fullTitle: String? = null,
    val urlPart: String? = null,
    val color: String? = null,
    val radioIcon: Icon? = null,
    val subGenres: List<Genre>? = null,
    val hideInRegions: List<Int>? = null
)
