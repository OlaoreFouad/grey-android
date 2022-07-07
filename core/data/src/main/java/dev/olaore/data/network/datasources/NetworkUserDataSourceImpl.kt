package dev.olaore.data.network.datasources

import dev.olaore.data.network.GitHubService
import dev.olaore.domain.datasources.network.NetworkUserDataSource
import dev.olaore.domain.models.users.User
import javax.inject.Inject

class NetworkUserDataSourceImpl @Inject constructor (
    private val gitHubService: GitHubService
) : NetworkUserDataSource {

    override suspend fun getUsers(query: String): Result<List<User>> {
        TODO("Not yet implemented")
    }

}
