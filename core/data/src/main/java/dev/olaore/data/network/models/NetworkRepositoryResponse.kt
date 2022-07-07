package dev.olaore.data.network.models

import com.google.gson.annotations.SerializedName
import dev.olaore.domain.models.repositories.Repository
import dev.olaore.domain.models.repositories.RepositoryDetail

data class NetworkRepositoryResponse(
    val id: Int,
    val name: String?,
    @SerializedName("full_name") val fullname: String?,
    val owner: NetworkRepositoryOwner,
    val description: String?,
    val language: String?,
    @SerializedName("stargazers_count") val stars: Int,
    val topics: List<String>
)

data class NetworkRepositoryOwner(
    val login: String?,
    val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String?,
    val url: String
)

data class NetworkRepositoryDetailsResponse(
    val id: Int,
    @SerializedName("full_name") val fullname: String?,
    val private: Boolean,
    @SerializedName("stargazers_count") val stars: Int,
    val language: String?,
    val description: String?,
    @SerializedName("updated_at") val lastUpdatedAt: String?
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
        topics = topics
    )
}

fun NetworkRepositoryDetailsResponse.toRepositoryDetail(): RepositoryDetail {
    return RepositoryDetail(
        id = id,
        fullname = fullname.orEmpty(),
        description = description.orEmpty(),
        language = language.orEmpty(),
        stars = stars,
        private = private,
        lastUpdatedAt = lastUpdatedAt.orEmpty()
    )
}
