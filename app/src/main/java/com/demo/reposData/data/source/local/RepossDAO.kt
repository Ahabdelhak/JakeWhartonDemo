package com.demo.reposData.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.reposData.data.model.ReposDataEntity

@Dao
interface RepossDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReposData(items: List<ReposDataEntity>)

    @Query("SELECT * FROM Repos_Data ORDER BY id ASC")
    suspend fun getReposData(): List<ReposDataEntity>

    @Query("DELETE FROM Repos_Data")
    suspend fun deleteReposData()

}