package com.demo.reposData.di

import com.demo.reposData.data.source.local.RepossDAO
import com.demo.reposData.data.source.local.RoomDataSourceImpl
import com.demo.reposData.data.source.remote.ReposService
import com.demo.reposData.data.source.remote.RemoteDataSourceImpl
import com.demo.reposData.domain.source.local.LocalDataSource
import com.demo.reposData.domain.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        ReposService: ReposService,
    ): RemoteDataSource =
        RemoteDataSourceImpl(ReposService)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        RepossDAO: RepossDAO
    ): LocalDataSource = RoomDataSourceImpl(RepossDAO)
}