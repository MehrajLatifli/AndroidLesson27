package com.example.weathertomorrow.source.api.repositories

import android.util.Log
import com.example.weathertomorrow.models.responses.get.WeatherResponse
import com.example.weathertomorrow.source.api.IApiManager
import com.example.weathertomorrow.source.api.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: IApiManager) {


    suspend fun getRealtimeWeatherbyCity(city:String) : Resource<WeatherResponse>{

        return safeApiRequest {
            api.getRealtimeWeatherbyCity(city)
        }
    }

    suspend fun <T> safeApiRequest(request: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {

                val response = request.invoke()

                if (response.isSuccessful) {
                    Resource.Success(response.body())
                } else {
                    Log.e("Response error",response.message())
                    Resource.Error(response.message())
                }
            } catch (e: Exception) {
                Log.e("APIFailed",e.message.toString())
                Resource.Error(e.localizedMessage)
            }
        }
    }
}
