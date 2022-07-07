package dev.olaore.greyandroid.repositories

import dev.olaore.domain.common.Result
import dev.olaore.domain.datasources.network.NetworkRepositoryDataSource
import dev.olaore.domain.datasources.network.NetworkUserDataSource
import dev.olaore.domain.models.repositories.Repository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class GitHubRepository @Inject constructor(
    private val networkRepositoryDataSource: NetworkRepositoryDataSource,
    private val networkUserDataSource: NetworkUserDataSource
) {

    suspend fun getRepositories(query: String): Result<List<Repository>> {
        return withContext(Dispatchers.IO) {
            networkRepositoryDataSource.getRepositories(query)
        }
    }

    suspend fun getUsers(query: String) {
    }

}
