package com.demo.reposData.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.demo.reposData.common.Resource
import com.demo.reposData.data.mappers.*
import com.demo.reposData.data.source.local.RepossRoomDB
import com.demo.reposData.domain.repository.ReposRepository
import com.demo.reposData.domain.source.local.LocalDataSource
import com.demo.reposData.domain.source.remote.RemoteDataSource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import javax.inject.Named

class ReposRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val RepossRoomDB: RepossRoomDB,
    @Named("Default") private val coContextDefault: CoroutineDispatcher
) : ReposRepository {

    private var job: Job? = null

    override fun remoteRepossData(page : Int) = flow {
        emit(Resource.Loading)

        val response = try {
            remoteDataSource.getReposData(page)
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable))
            null
        }

            response?.let { data ->
                if(page==1) {
                    localDataSource.deleteReposData()
                    RepossRoomDB.withTransaction {
                        localDataSource.insertReposDataList(data.toReposDataEntity())
                    }
                }
                emit(Resource.Success(response))
            }
    }


    override fun offlineRepossData() = flow {
        emit(Resource.Loading)

        val cache = localDataSource.getReposData()

        if (cache.isNotEmpty()) {
            emit(Resource.Success(cache.toReposDataUI()))
        }
    }


}