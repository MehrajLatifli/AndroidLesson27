package com.example.weathertomorrow.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertomorrow.models.entities.WeatherEntity
import com.example.weathertomorrow.models.responses.get.WeatherResponse
import com.example.weathertomorrow.source.api.Resource
import com.example.weathertomorrow.source.api.repositories.WeatherRepository
import com.example.weathertomorrow.source.local.mapping.toWeatherEntity
import com.example.weathertomorrow.source.local.mapping.toWeatherResponse
import com.example.weathertomorrow.source.local.repositories.EntityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
            repo.getRealtimeWeatherbyCity(city).collectLatest { response: Resource<WeatherResponse> ->
                    when (response) {
                        is Resource.Success -> {
                            _loading.value = false
                            val itemResponse = response.data
                            if (itemResponse != null) {
                                _weathers.value = listOf(itemResponse)
                                Log.e("Weather", _weathers.value.toString())

                                val weatherEntity = itemResponse.toWeatherEntity()

                                try {
                                    delay(150)
                                    repoEntity.addWeatherEntity(weatherEntity)
                                } catch (e: Exception) {
                                    Log.e(
                                        "DatabaseError",
                                        "Error adding weather entity: ${e.message}"
                                    )
                                }
                            } else {
                                _error.value = "No weather found"
                                _weathers.value = emptyList()
                                Log.e("APIFailed", _error.value.toString())
                            }
                        }

                        is Resource.Error -> {
                            _loading.value = false
                            _error.value = "Failed to fetch weather: ${response.message}"
                            Log.e("APIFailed", _error.value.toString())

                            try {
                                _loading.value = true
                                delay(150)

                                val weatherEntities = repoEntity.getWeatherEntities()

                                weatherEntities.collectLatest { entities: List<WeatherEntity> ->
                                    delay(150)

                                    if (entities.isNotEmpty()) {
                                        _weathers.value = entities.map { it.toWeatherResponse() }
                                    } else {
                                        _error.value = "No cached weather found"
                                        _weathers.value = emptyList()
                                    }

                                    _loading.value = false
                                }
                            } catch (e: Exception) {
                                Log.e(
                                    "DatabaseError",
                                    "Error getting weather entities: ${e.message}"
                                )

                                delay(150)
                                _error.value = "Error getting cached data: ${e.message}"
                                _weathers.value = emptyList()
                            }
                        }
                    }
                }
        }
    }
}
