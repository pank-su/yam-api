package model.account

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import model.Result
import kotlin.reflect.KMutableProperty1

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
) : Result() {
    suspend fun <T> update(property: KMutableProperty1<UserSettings, T>, newValue: T): UserSettings {
        return update(hashMapOf(property to newValue))
    }

    suspend fun update(propertyToValues: HashMap<KMutableProperty1<UserSettings, *>, *>): UserSettings {
        val normalHashMap = hashMapOf<String, String>(

        )
        propertyToValues.forEach {
            normalHashMap[it.key.name] = it.value!!.toString().lowercase()
        }

        return client!!.requestForm<UserSettings>("account", "settings", body =  normalHashMap)
    }
}
