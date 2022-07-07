package dev.olaore.domain.datasources.network

import dev.olaore.domain.common.Result
import dev.olaore.domain.models.users.User

interface NetworkUserDataSource {
    suspend fun getUsers(query: String): Result<List<User>>
}
