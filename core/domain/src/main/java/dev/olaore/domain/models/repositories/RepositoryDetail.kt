package dev.olaore.domain.models.repositories

data class RepositoryDetail(
    val id: Int,
    val fullname: String,
    val private: Boolean,
    val description: String,
    val stars: Int,
    val language: String,
    val lastUpdatedAt: String
)
