package com.nprmanbrandons11.smartweather.data.remote.service

import com.google.gson.JsonObject
import com.nprmanbrandons11.smartweather.data.remote.Retrofit
import kotlinx.coroutines.withContext

class WeatherService {
    private val retrofit = Retrofit.getRetrofit()

    suspend fun getWeather(){

    }
}