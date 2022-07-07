package dev.olaore.domain.models.user

import dev.olaore.domain.models.repositories.RepositoryDetail

data class UserDetail(
    val fullname: String,
    val username: String,
    val imageUrl: String,
    val bio: String,
    val location: String,
    val email: String,
    val id: Int,
    val followers: Int,
    val following: Int,
    val blog: String,
    val repositories: List<RepositoryDetail>
)
