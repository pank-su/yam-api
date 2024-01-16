package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val id: String,
    @SerialName("client_id") val clientId: String,
    val login: String,
    @SerialName("display_name") val displayName: String,
    @SerialName("real_name") val realName: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    val sex: String,
    @SerialName("default_email") val defaultEmail: String,
    val emails: List<String>,
    val birthday: String,
    @SerialName("default_avatar_id") val defaultAvatarId: String,
    @SerialName("is_avatar_empty") val isAvatarEmpty: Boolean,
    val psuid: String
){
    val avatarLink = "https://avatars.yandex.net/get-yapic/${defaultAvatarId}/islands-200"
}