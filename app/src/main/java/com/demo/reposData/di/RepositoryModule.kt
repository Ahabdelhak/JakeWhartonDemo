package com.demo.reposData.di

import com.demo.reposData.data.repository.ReposRepositoryImpl
import com.demo.reposData.data.source.local.RepossRoomDB
import com.demo.reposData.domain.repository.ReposRepository
import com.demo.reposData.domain.source.local.LocalDataSource
import com.demo.reposData.domain.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideReposRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        RepossRoomDB: RepossRoomDB,
        @Named("Default") coContextDefault: CoroutineDispatcher
    ): ReposRepository =
        ReposRepositoryImpl(remoteDataSource, localDataSource, RepossRoomDB, coContextDefault)
}