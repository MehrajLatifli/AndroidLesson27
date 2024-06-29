// HomeViewModel.kt

package com.example.androidlesson27.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidlesson27.models.responses.get.WeatherResponse
import com.example.androidlesson27.source.api.Resource
import com.example.androidlesson27.source.api.repositories.WeatherRepository
import com.example.androidlesson27.source.local.mapping.toWeatherEntity
import com.example.androidlesson27.source.local.mapping.toWeatherResponse
import com.example.androidlesson27.source.local.repositories.EntityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: WeatherRepository,
    private val repoEntity: EntityRepository
) : ViewModel() {

    private val _weathers = MutableLiveData<List<WeatherResponse>>()
    val weathers: LiveData<List<WeatherResponse>> = _weathers

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        _loading.value = false
        _error.value = null
    }

    fun getRealtimeWeatherbyCity(city: String) {
        _loading.value = true
        viewModelScope.launch {
            val response = repo.getRealtimeWeatherbyCity(city)
            when (response) {
                is Resource.Success -> {
                    _loading.value = false
                    val itemResponse = response.data
                    if (itemResponse != null) {
                        _weathers.value = listOf(itemResponse)
                        Log.e("Weather", _weathers.value.toString())

                        itemResponse.toWeatherEntity().let { weatherEntity ->
                            delay(500)
                            withContext(Dispatchers.IO) {
                                try {
                                    repoEntity.addWeatherEntity(weatherEntity)
                                } catch (e: Exception) {
                                    Log.e("DatabaseError", "Error adding weather entity: ${e.message}")
                                }
                            }
                        }
                    } else {
                        _error.value = "No weathers found"
                        _weathers.value = emptyList()
                        Log.e("APIFailed", _error.value.toString())
                    }
                }
                is Resource.Error -> {
                    _loading.value = false
                    _error.value = "Failed to fetch weathers: ${response.message}"
                    Log.e("APIFailed", _error.value.toString())

                    try {
                        val weatherEntities = repoEntity.getWeatherEntity()
                        if (weatherEntities.isNotEmpty()) {
                            _weathers.value = weatherEntities.map { it.toWeatherResponse() }
                        } else {
                            _error.value = "No cached weather found"
                            _weathers.value = emptyList()
                        }
                    } catch (e: Exception) {
                        Log.e("DatabaseError", "Error getting weather entities: ${e.message}")
                        _error.value = "Error converting cached data: ${e.message}"
                        _weathers.value = emptyList()
                    }
                }
            }
        }
    }
}
