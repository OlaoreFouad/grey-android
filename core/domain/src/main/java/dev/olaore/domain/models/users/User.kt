package dev.olaore.domain.models.users

data class User(
    val fullname: String,
    val username: String,
    val imageUrl: String,
    val bio: String,
    val location: String,
    val email: String,
    val id: Int,
    val userUrl: String
)
