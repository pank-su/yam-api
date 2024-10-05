package su.pank.yamusic.account.model

import kotlinx.datetime.Instant
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class UserSettings(
    var uid: Int? = null,
    var lastFmScrobblingEnabled: Boolean,
    var facebookScrobblingEnabled: Boolean,
    var shuffleEnabled: Boolean,
    var addNewTrackOnPlaylistTop: Boolean,
    var volumePercents: Int,
    var userMusicVisibility: Visibility,
    var userSocialVisibility: Visibility,
    var modified: Instant? = null,
    var theme: Theme,
    var promosDisabled: Boolean,
    var autoPlayRadio: Boolean,
    var syncQueueEnabled: Boolean,
    var childModEnabled: Boolean?,
    var adsDisabled: Boolean?
) {
//    suspend fun <T> update(property: KMutableProperty1<UserSettings, T>, newValue: T): UserSettings {
//        return update(hashMapOf(property to newValue))
//    }
//
//    suspend fun update(propertyToValues: HashMap<KMutableProperty1<UserSettings, *>, *>): UserSettings {
//        val normalHashMap = hashMapOf<String, String>(
//
//        )
//        propertyToValues.forEach {
//            normalHashMap[it.key.name] = it.value!!.toString().lowercase()
//        }
//
//        return client!!.requestForm<UserSettings>("account", "settings", body =  normalHashMap)
//    }
}

@Serializable
enum class Theme {
    @SerialName("white")
    White,

    @SerialName("black")
    Black
}

@OptIn(ExperimentalSerializationApi::class)
@Serializable
enum class Visibility {
    @JsonNames("PUBLIC", "public")
    PUBLIC,

    @JsonNames("PRIVATE", "private")
    PRIVATE
}