package dev.olaore.data.network.datasources

import dev.olaore.data.network.GitHubService
import dev.olaore.data.network.models.toUser
import dev.olaore.data.util.safeNetworkCall
import dev.olaore.domain.common.Result
import dev.olaore.domain.datasources.network.NetworkUserDataSource
import dev.olaore.domain.models.users.User
import javax.inject.Inject

class NetworkUserDataSourceImpl @Inject constructor (
    private val gitHubService: GitHubService
) : NetworkUserDataSource {

    override suspend fun getUsers(query: String): Result<List<User>> {
        return safeNetworkCall {
            val response = gitHubService.getUsers(query)
            response.items.map { it.toUser() }
        }
    }

}
