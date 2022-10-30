package com.demo.reposData.di

import android.content.Context
import androidx.room.Room
import com.demo.reposData.data.source.local.RepossDAO
import com.demo.reposData.data.source.local.RepossRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideReposRoomDB(@ApplicationContext appContext: Context): RepossRoomDB =
        Room.databaseBuilder(
            appContext,
            RepossRoomDB::class.java,
            "main.db"
        ).build()

    @Provides
    @Singleton
    fun provideRepossDAO(RepossRoomDB: RepossRoomDB): RepossDAO =
        RepossRoomDB.RepossDAO()
}