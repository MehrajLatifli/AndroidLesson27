package com.example.weathertomorrow.source.api.repositories

import android.util.Log
import com.example.weathertomorrow.models.responses.get.WeatherResponse
import com.example.weathertomorrow.source.api.IApiManager
import com.example.weathertomorrow.source.api.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: IApiManager) {


    /*
    suspend fun getRealtimeWeatherbyCity(city:String) : Resource<WeatherResponse>{

        return safeApiRequest {
            api.getRealtimeWeatherbyCity(city)
        }.single()
    }
    */



   suspend fun getRealtimeWeatherbyCity(city:String) =safeApiRequest{

        api.getRealtimeWeatherbyCity(city)

    }


    suspend fun <T> safeApiRequest(request: suspend () -> Response<T>) = flow<Resource<T>> {

        try {

            if (request.invoke().isSuccessful) {
                emit(Resource.Success(request().body()))
            } else {
                Log.e("Response error", request().message())
                emit(Resource.Error(request().errorBody().toString()))
            }
        } catch (e: Exception) {
            Log.e("APIFailed", e.message.toString())
            emit(Resource.Error(e.localizedMessage))
        }

    }.catch {
        Log.e("APIFailed", it.localizedMessage.toString())
        emit(Resource.Error(it.localizedMessage.toString()))
    }.flowOn(Dispatchers.IO)
}
