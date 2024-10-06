package rotor.model

import kotlinx.serialization.Serializable
import model.Icon
import su.pank.yamusic.rotor.model.Restrictions

@Serializable
data class Station(
    val id: Id,
    val name: String,
    val icon: Icon,
    val mtsIcon: Icon,
    val idForFrom: String,
    val restrictions: Restrictions,
    val restrictions2: Restrictions,
    val fullImageUrl: String? = null,
    val parentId: Id? = null
)


@Serializable
data class Id(val type: String, val tag: String) {
    override fun toString(): String = "$type:$tag"
}
