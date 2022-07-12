package com.nprmanbrandons11.smartweather.data.remote.service

import com.google.gson.JsonObject
import com.nprmanbrandons11.smartweather.data.models.response.WeatherResponse
import com.nprmanbrandons11.smartweather.data.remote.Retrofit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class WeatherService(private val dispatcher: CoroutineDispatcher = Dispatchers.Default) {
    private val retrofit = Retrofit.getRetrofit()
    private val client = retrofit.create(WeatherApi::class.java)

    suspend fun getWeather(latitude:Int,longitude:Int):WeatherResponse?{
        return withContext(dispatcher) {
            try {
                val response = client.getWeather(latitude,longitude)
                response.body()
            }catch (exception: Exception){
                return@withContext null
            }
        }
    }
}