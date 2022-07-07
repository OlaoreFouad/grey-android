package dev.olaore.domain.models.repositories

data class Repository(
    val id: Int,
    val name: String,
    val fullname: String,
    val owner: String,
    val imageUrl: String,
    val description: String,
    val tags: String,
    val stars: Int,
    val lang: String
)
