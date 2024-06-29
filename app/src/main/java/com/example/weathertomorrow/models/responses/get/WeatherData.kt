package com.example.weathertomorrow.models.responses.get


import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("time")
    val time: String,
    @SerializedName("values")
    val weatherValue: WeatherValue
)