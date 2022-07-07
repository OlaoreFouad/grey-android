package dev.olaore.domain.datasources.network

import dev.olaore.domain.common.Result
import dev.olaore.domain.models.repositories.Repository

interface NetworkRepositoryDataSource {
    suspend fun getRepositories(query: String): Result<List<Repository>>
}
