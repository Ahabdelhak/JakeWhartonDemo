package com.demo.reposData.domain.source.local

import com.demo.reposData.data.model.ReposDataEntity

interface LocalDataSource {

    suspend fun insertReposDataList(items: List<ReposDataEntity>)

    suspend fun getReposData(): List<ReposDataEntity>

    suspend fun deleteReposData()
}