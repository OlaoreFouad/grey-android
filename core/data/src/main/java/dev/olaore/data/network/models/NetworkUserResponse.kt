package dev.olaore.data.network.models

import dev.olaore.domain.models.users.User

data class NetworkUserResponse(
    val id: Int,
    val login: String?,
)

fun NetworkUserResponse.toUser(): User {
    return User(
        id = id,
        username = login.orEmpty(),
        fullname = "",
        imageUrl = "",
        bio = "",
        email = "",
        location = ""
    )
}
