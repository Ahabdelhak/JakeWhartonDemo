package com.demo.reposData.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.reposData.data.model.ReposDataEntity

@Database(
    entities = [ReposDataEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RepossRoomDB : RoomDatabase() {
    abstract fun RepossDAO(): RepossDAO
}