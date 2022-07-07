package dev.olaore.data.network.models

import com.google.gson.annotations.SerializedName
import dev.olaore.domain.models.repositories.RepositoryDetail
import dev.olaore.domain.models.user.UserDetail
import dev.olaore.domain.models.users.User

data class NetworkUserResponse(
    val id: Int,
    val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    val url: String,
    @SerializedName("repos_url") val repositoriesUrl: String
)

data class NetworkUserDetailsResponse(
    val id: Int,
    val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    val url: String,
    val bio: String?,
    val email: String?,
    val location: String?,
    val name: String?,
    val followers: Int?,
    val following: Int?,
    val blog: String?,
    @SerializedName("repos_url") val repositoriesUrl: String
)

fun NetworkUserDetailsResponse.toUser(): User {
    return User(
        id = id,
        username = login.orEmpty(),
        fullname = name.orEmpty(),
        imageUrl = avatarUrl.orEmpty(),
        bio = bio.orEmpty(),
        email = email.orEmpty(),
        location = location.orEmpty(),
        userUrl = url
    )
}

fun NetworkUserDetailsResponse.toUserDetail(repositories: List<RepositoryDetail>): UserDetail {
    return UserDetail(
        fullname = name.orEmpty(),
        username = login.orEmpty(),
        imageUrl = avatarUrl.orEmpty(),
        bio = bio.orEmpty(),
        location = location.orEmpty(),
        email = email.orEmpty(),
        followers = followers ?: 0,
        following = following ?: 0,
        id = id,
        repositories = repositories,
        blog = blog.orEmpty()
    )
}
