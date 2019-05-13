package com.aleksejantonov.n2048.db.impl.di

import android.content.Context
import androidx.room.Room
import com.aleksejantonov.n2048.db.api.data.DatabaseClientApi
import com.aleksejantonov.n2048.db.impl.data.GameDatabaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideDatabaseClientApi(): DatabaseClientApi {
        return Room.databaseBuilder(context, GameDatabaseImpl::class.java, "2048_db").build()
    }
}