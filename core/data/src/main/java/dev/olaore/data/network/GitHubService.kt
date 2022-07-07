package dev.olaore.data.network

import dev.olaore.data.network.models.NetworkRepositoryResponseContainer
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

    @GET("users")
    suspend fun getUsers(query: String)

    @GET("repositories")
    suspend fun getRepositories(
        @Query("q") query: String
    ): NetworkRepositoryResponseContainer

}
