package model.account

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
enum class Visibility {
    @JsonNames("PUBLIC", "public")
    PUBLIC,

    @JsonNames("PRIVATE", "private")
    PRIVATE
}