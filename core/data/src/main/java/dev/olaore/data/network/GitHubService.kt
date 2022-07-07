package dev.olaore.data.network

import dev.olaore.data.network.models.NetworkRepositoryDetailsResponse
import dev.olaore.data.network.models.NetworkRepositoryResponseContainer
import dev.olaore.data.network.models.NetworkUserDetailsResponse
import dev.olaore.data.network.models.NetworkUserResponse
import dev.olaore.data.network.models.NetworkUserResponseContainer
import dev.olaore.data.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GitHubService {

    @GET("users")
    suspend fun getUsers(
        @Query("q") query: String,
        @Query("per_page") pageCount: Int = Constants.DEFAULT_PAGE_COUNT
    ): NetworkUserResponseContainer

    @GET("repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("per_page") pageCount: Int = Constants.DEFAULT_PAGE_COUNT
    ): NetworkRepositoryResponseContainer

    @GET
    suspend fun getUser(@Url userUrl: String): NetworkUserDetailsResponse

    @GET
    suspend fun getUserRepositories(@Url repositoriesUrl: String): List<NetworkRepositoryDetailsResponse>

}
