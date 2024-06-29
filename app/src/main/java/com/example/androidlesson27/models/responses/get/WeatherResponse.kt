package com.example.androidlesson27.models.responses.get


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("data")
    val weatherData: WeatherData,
    @SerializedName("location")
    val location: Location
)