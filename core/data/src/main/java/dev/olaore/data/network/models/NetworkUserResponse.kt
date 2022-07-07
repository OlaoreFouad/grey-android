package dev.olaore.data.network.models

import com.google.gson.annotations.SerializedName
import dev.olaore.domain.models.users.User

data class NetworkUserResponse(
    val id: Int,
    val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    val url: String
)

data class NetworkUserDetailsResponse(
    val id: Int,
    val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    val url: String,
    val bio: String?,
    val email: String?,
    val location: String?,
    val name: String?
)

fun NetworkUserDetailsResponse.toUser(): User {
    return User(
        id = id,
        username = login.orEmpty(),
        fullname = name.orEmpty(),
        imageUrl = avatarUrl.orEmpty(),
        bio = bio.orEmpty(),
        email = email.orEmpty(),
        location = location.orEmpty()
    )
}
