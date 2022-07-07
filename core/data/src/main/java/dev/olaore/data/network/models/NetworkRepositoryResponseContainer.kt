package dev.olaore.data.network.models

import com.google.gson.annotations.SerializedName

data class NetworkRepositoryResponseContainer(
    @SerializedName("total_count") val totalCount: Int,
    val items: List<NetworkRepositoryResponse>
)
