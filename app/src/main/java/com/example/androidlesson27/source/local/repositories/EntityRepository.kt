package com.example.androidlesson27.source.local.repositories

import com.example.androidlesson27.models.entities.WeatherEntity
import com.example.androidlesson27.source.local.LocalDao
import javax.inject.Inject

class EntityRepository @Inject constructor(
    val localDao: LocalDao
){
    fun addWeatherEntity(weatherEntity: WeatherEntity) = localDao.add(weatherEntity)

    suspend fun getWeatherEntity() = localDao.getweathers()
}