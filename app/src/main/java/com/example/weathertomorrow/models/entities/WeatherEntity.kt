package com.example.weathertomorrow.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Weathers")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "weather_id")
    val id: Int = 0,

    val city: String,
    val time: String,
    val cloudBase: Double,
    val cloudCeiling: Double,
    val cloudCover: Double,
    val dewPoint: Double,
    val freezingRainIntensity: Double,
    val humidity: Double,
    val precipitationProbability: Double,
    val pressureSurfaceLevel: Double,
    val rainIntensity: Double,
    val sleetIntensity: Double,
    val snowIntensity: Double,
    val temperature: Double,
    val temperatureApparent: Double,
    val uvHealthConcern: Double,
    val uvIndex: Double,
    val visibility: Double,
    val weatherCode: Double,
    val windDirection: Double,
    val windGust: Double,
    val windSpeed: Double,

    @Ignore
    val lat: Double = 0.0,

    @Ignore
    val lon: Double = 0.0,

    @Ignore
    val type: String = ""
) {
    constructor(
        id: Int,
        city: String,
        time: String,
        cloudBase: Double,
        cloudCeiling: Double,
        cloudCover: Double,
        dewPoint: Double,
        freezingRainIntensity: Double,
        humidity: Double,
        precipitationProbability: Double,
        pressureSurfaceLevel: Double,
        rainIntensity: Double,
        sleetIntensity: Double,
        snowIntensity: Double,
        temperature: Double,
        temperatureApparent: Double,
        uvHealthConcern: Double,
        uvIndex: Double,
        visibility: Double,
        weatherCode: Double,
        windDirection: Double,
        windGust: Double,
        windSpeed: Double
    ) : this(
        id,
        city,
        time,
        cloudBase,
        cloudCeiling,
        cloudCover,
        dewPoint,
        freezingRainIntensity,
        humidity,
        precipitationProbability,
        pressureSurfaceLevel,
        rainIntensity,
        sleetIntensity,
        snowIntensity,
        temperature,
        temperatureApparent,
        uvHealthConcern,
        uvIndex,
        visibility,
        weatherCode,
        windDirection,
        windGust,
        windSpeed,
        0.0,
        0.0,
        ""
    )
}
