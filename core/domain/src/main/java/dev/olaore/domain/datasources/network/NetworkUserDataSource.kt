package dev.olaore.domain.datasources.network

import dev.olaore.domain.common.Result
import dev.olaore.domain.models.user.UserDetail
import dev.olaore.domain.models.users.User

interface NetworkUserDataSource {
    suspend fun getUsers(query: String): Result<List<User>>
    suspend fun getUser(userUrl: String): Result<UserDetail>
}
