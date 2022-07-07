package dev.olaore.data.network.models

import com.google.gson.annotations.SerializedName
import dev.olaore.domain.models.repositories.Repository

data class NetworkRepositoryResponse(
    val id: Int,
    val name: String?,
    @SerializedName("full_name") val fullname: String?,
    val owner: NetworkRepositoryOwner,
    val description: String?,
    val language: String?,
    @SerializedName("stargazers_count") val stars: Int
)

data class NetworkRepositoryOwner(
    val login: String?,
    val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String?,
    val url: String
)

fun NetworkRepositoryResponse.toRepository(): Repository {
    return Repository(
        id = id,
        name = name.orEmpty(),
        fullname = fullname.orEmpty(),
        description = description.orEmpty(),
        imageUrl = owner.avatarUrl.orEmpty(),
        lang = language.orEmpty(),
        owner = owner.login.orEmpty(),
        stars = stars,
        tags = ""
    )
}
