// WeatherMap.kt

package com.example.weathertomorrow.source.local.mapping

import com.example.weathertomorrow.models.entities.WeatherEntity
import com.example.weathertomorrow.models.responses.get.Location
import com.example.weathertomorrow.models.responses.get.WeatherData
import com.example.weathertomorrow.models.responses.get.WeatherResponse
import com.example.weathertomorrow.models.responses.get.WeatherValue

fun WeatherEntity.toWeatherResponse(): WeatherResponse {
    val weatherData = WeatherData(
        time = this.time,
        weatherValue = WeatherValue(
            cloudBase = this.cloudBase,
            cloudCeiling = this.cloudCeiling,
            cloudCover = this.cloudCover,
            dewPoint = this.dewPoint,
            freezingRainIntensity = this.freezingRainIntensity,
            humidity = this.humidity,
            precipitationProbability = this.precipitationProbability,
            pressureSurfaceLevel = this.pressureSurfaceLevel,
            rainIntensity = this.rainIntensity,
            sleetIntensity = this.sleetIntensity,
            snowIntensity = this.snowIntensity,
            temperature = this.temperature,
            temperatureApparent = this.temperatureApparent,
            uvHealthConcern = this.uvHealthConcern,
            uvIndex = this.uvIndex,
            visibility = this.visibility,
            weatherCode = this.weatherCode,
            windDirection = this.windDirection,
            windGust = this.windGust,
            windSpeed = this.windSpeed
        )
    )

    val location = Location(
        name = this.city,
        lat = this.lat,
        lon = this.lon,
        type = this.type
    )

    return WeatherResponse(
        weatherData = weatherData,
        location = location
    )
}

fun WeatherResponse.toWeatherEntity(): WeatherEntity {
    val weatherData = this.weatherData
    val weatherValue = weatherData.weatherValue
    val location = this.location

    return WeatherEntity(
        city = location.name,
        time = weatherData.time,
        cloudBase = weatherValue.cloudBase,
        cloudCeiling = weatherValue.cloudCeiling,
        cloudCover = weatherValue.cloudCover,
        dewPoint = weatherValue.dewPoint,
        freezingRainIntensity = weatherValue.freezingRainIntensity,
        humidity = weatherValue.humidity,
        precipitationProbability = weatherValue.precipitationProbability,
        pressureSurfaceLevel = weatherValue.pressureSurfaceLevel,
        rainIntensity = weatherValue.rainIntensity,
        sleetIntensity = weatherValue.sleetIntensity,
        snowIntensity = weatherValue.snowIntensity,
        temperature = weatherValue.temperature,
        temperatureApparent = weatherValue.temperatureApparent,
        uvHealthConcern = weatherValue.uvHealthConcern,
        uvIndex = weatherValue.uvIndex,
        visibility = weatherValue.visibility,
        weatherCode = weatherValue.weatherCode,
        windDirection = weatherValue.windDirection,
        windGust = weatherValue.windGust,
        windSpeed = weatherValue.windSpeed,
        lat = location.lat,
        lon = location.lon,
        type = location.type
    )
}
