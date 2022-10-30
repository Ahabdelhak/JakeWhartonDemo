package com.demo.reposData.data.source.remote

import com.demo.reposData.common.Constants.Repos_Data
import com.demo.reposData.data.model.JackwhartonResponse
import com.demo.reposData.ui.main.MainFragment.Companion.TOTAL_PAGES
import retrofit2.http.GET
import retrofit2.http.Query

interface ReposService {
    @GET(Repos_Data)
    suspend fun getReposData(@Query("page") page : Int,@Query("per_page") per_page: Int = TOTAL_PAGES): JackwhartonResponse

}