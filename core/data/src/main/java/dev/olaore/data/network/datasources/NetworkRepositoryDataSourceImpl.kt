package dev.olaore.data.network.datasources

import dev.olaore.data.network.GitHubService
import dev.olaore.data.network.models.toRepository
import dev.olaore.data.util.safeNetworkCall
import dev.olaore.domain.common.Result
import dev.olaore.domain.datasources.network.NetworkRepositoryDataSource
import dev.olaore.domain.models.repositories.Repository
import javax.inject.Inject

class NetworkRepositoryDataSourceImpl @Inject constructor (
    private val githubService: GitHubService
) : NetworkRepositoryDataSource {

    override suspend fun getRepositories(query: String): Result<List<Repository>> {
        return safeNetworkCall {
            val response = githubService.getRepositories(query)
            response.items.map { it.toRepository() }
        }
    }

}
