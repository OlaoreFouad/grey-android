package dev.olaore.greyandroid.repositories

import dev.olaore.domain.common.Result
import dev.olaore.domain.datasources.network.NetworkRepositoryDataSource
import dev.olaore.domain.datasources.network.NetworkUserDataSource
import dev.olaore.domain.models.repositories.Repository
import dev.olaore.domain.models.user.UserDetail
import dev.olaore.domain.models.users.User
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

    suspend fun getUsers(query: String): Result<List<User>> {
        return withContext(Dispatchers.IO) {
            networkUserDataSource.getUsers(query)
        }
    }

    suspend fun getUser(url: String): Result<UserDetail> {
        return withContext(Dispatchers.IO) {
            networkUserDataSource.getUser(url)
        }
    }

}
