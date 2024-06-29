package com.example.weathertomorrow.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weathertomorrow.models.entities.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 2)
abstract class WeatherDatabase : RoomDatabase(){
    abstract fun createDAO(): LocalDao
}