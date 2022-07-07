package dev.olaore.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.olaore.data.network.datasources.NetworkRepositoryDataSourceImpl
import dev.olaore.data.network.datasources.NetworkUserDataSourceImpl
import dev.olaore.domain.datasources.network.NetworkRepositoryDataSource
import dev.olaore.domain.datasources.network.NetworkUserDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsNetworkRepositoryDataSource(
        networkRepositoryDataSourceImpl: NetworkRepositoryDataSourceImpl
    ): NetworkRepositoryDataSource

    @Binds
    abstract fun bindsNetworkUserDataSource(
        networkUserDataSourceImpl: NetworkUserDataSourceImpl
    ): NetworkUserDataSource

}
