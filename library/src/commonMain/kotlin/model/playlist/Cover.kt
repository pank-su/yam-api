package model.playlist

import kotlinx.serialization.Serializable

@Serializable
data class Cover(
    val type: CoverType,
    val uri: String,
    val prefix: String? = null,
    val dir: String? = null,
    val version: String? = null,
    val custom: Boolean? = null
) {


    fun getUrl(size: CoverSize) = "https://${uri.replace("%%", size.toString())}"
}
