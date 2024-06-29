package com.example.androidlesson27.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidlesson27.models.entities.WeatherEntity

@Dao
interface LocalDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun add(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weathers")
    suspend fun getweathers(): List<WeatherEntity>

}