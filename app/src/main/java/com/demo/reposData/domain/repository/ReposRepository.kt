package com.demo.reposData.domain.repository

import com.demo.reposData.common.Resource
import com.demo.reposData.data.model.JackwhartonResponse
import com.demo.reposData.domain.model.ReposDataUI
import kotlinx.coroutines.flow.Flow

interface ReposRepository {
    fun remoteRepossData(page : Int): Flow<Resource<JackwhartonResponse>>
    fun offlineRepossData(): Flow<Resource<List<ReposDataUI>>>
}