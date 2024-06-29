package com.example.androidlesson27.source.api

import com.example.androidlesson27.models.responses.get.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IApiManager {

    @Headers("Content-Type: application/json; charset=UTF-8","X-RateLimit-Limit-day: 1")

    @GET("realtime")
    suspend fun getRealtimeWeatherbyCity(
        @Query("location") city: String
    ):Response<WeatherResponse>

}