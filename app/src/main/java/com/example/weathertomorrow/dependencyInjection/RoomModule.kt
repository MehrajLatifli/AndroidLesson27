package com.example.weathertomorrow.dependencyInjection

import android.app.Application
import androidx.room.Room
import com.example.weathertomorrow.source.local.LocalDao
import com.example.weathertomorrow.source.local.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideLocalDatabase(application: Application): WeatherDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            WeatherDatabase::class.java,
            "Weather_db"
        ).build()
    }

    @Provides
    fun provideLocalDao(localDatabase: WeatherDatabase): LocalDao {
        return localDatabase.createDAO()
    }
}