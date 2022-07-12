package com.nprmanbrandons11.smartweather.domain.use_case


import android.util.Log
import com.nprmanbrandons11.smartweather.data.models.response.WeatherResponse
import com.nprmanbrandons11.smartweather.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class WeatherUsesCases {
    private val repository = HomeRepository()

    fun getWeather(latitude:Int,longitude:Int) :Flow<WeatherResponse?> = flow {
        emit(repository.getWeather(latitude,longitude))
    }.catch { e ->
        e.printStackTrace()
        Log.d("WEATHER",e.toString())
        }
}