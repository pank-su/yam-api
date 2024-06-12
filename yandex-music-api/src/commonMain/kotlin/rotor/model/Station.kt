package rotor.model

import kotlinx.serialization.Serializable
import model.Icon

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
