package com.demo.reposData.data.source.local

import com.demo.reposData.data.model.ReposDataEntity
import com.demo.reposData.domain.source.local.LocalDataSource
import javax.inject.Inject

class RoomDataSourceImpl @Inject constructor(
    private val repossDAO: RepossDAO
) : LocalDataSource {

    override suspend fun insertReposDataList(items: List<ReposDataEntity>) =
        repossDAO.insertReposData(items)

    override suspend fun getReposData() = repossDAO.getReposData()

    override suspend fun deleteReposData() = repossDAO.deleteReposData()

}