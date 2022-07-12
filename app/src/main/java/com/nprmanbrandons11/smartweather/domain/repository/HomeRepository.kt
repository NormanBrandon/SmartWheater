package com.nprmanbrandons11.smartweather.domain.repository

import com.nprmanbrandons11.smartweather.data.models.response.WeatherResponse
import com.nprmanbrandons11.smartweather.data.remote.service.WeatherService

class HomeRepository {
    private val api = WeatherService()

    suspend fun getWeather(latitude:Int,longitude:Int):WeatherResponse?{
        return api.getWeather(latitude,longitude)
    }
}