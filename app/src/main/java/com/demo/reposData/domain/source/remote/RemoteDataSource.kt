package com.demo.reposData.domain.source.remote

import com.demo.reposData.data.model.JackwhartonResponse

interface RemoteDataSource {
    suspend fun getReposData(page:Int): JackwhartonResponse
}