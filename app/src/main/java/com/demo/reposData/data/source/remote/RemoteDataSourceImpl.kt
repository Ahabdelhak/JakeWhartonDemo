package com.demo.reposData.data.source.remote

import com.demo.reposData.domain.source.remote.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val ReposService: ReposService
) : RemoteDataSource {

    override suspend fun getReposData(page:Int) = ReposService.getReposData(page)

}