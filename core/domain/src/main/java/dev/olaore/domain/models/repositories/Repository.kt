package dev.olaore.domain.models.repositories

data class Repository(
    val id: Int,
    val name: String,
    val fullname: String,
    val owner: String,
    val imageUrl: String,
    val description: String,
    val topics: List<String>,
    val stars: Int,
    val lang: String
)
