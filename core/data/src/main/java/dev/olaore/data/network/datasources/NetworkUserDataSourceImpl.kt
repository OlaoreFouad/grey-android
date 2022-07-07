package dev.olaore.data.network.datasources

import dev.olaore.data.network.GitHubService
import dev.olaore.data.network.models.toUser
import dev.olaore.data.util.safeNetworkCall
import dev.olaore.domain.common.Result
import dev.olaore.domain.datasources.network.NetworkUserDataSource
import dev.olaore.domain.models.users.User
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

class NetworkUserDataSourceImpl @Inject constructor (
    private val gitHubService: GitHubService
) : NetworkUserDataSource {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    override suspend fun getUsers(query: String): Result<List<User>> {
        return safeNetworkCall {
            val response = gitHubService.getUsers(query)
            val users: MutableList<Deferred<User>> = mutableListOf()
            response.items.forEach { user ->
                users.add(ioScope.async { gitHubService.getUser(user.url).toUser() })
            }
            users.awaitAll()
        }
    }

}
