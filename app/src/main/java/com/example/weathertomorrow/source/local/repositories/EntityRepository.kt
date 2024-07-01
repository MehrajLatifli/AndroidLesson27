package com.example.weathertomorrow.source.local.repositories

import com.example.weathertomorrow.models.entities.WeatherEntity
import com.example.weathertomorrow.source.local.LocalDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

class EntityRepository @Inject constructor(
    val localDao: LocalDao
){
    fun addWeatherEntity(weatherEntity: WeatherEntity) = localDao.add(weatherEntity)


    fun getWeatherEntities(): Flow<List<WeatherEntity>> {
        return localDao.getWeatherEntities()
    }
}