package com.nprmanbrandons11.smartweather.data.remote.service

import com.nprmanbrandons11.smartweather.data.models.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast?appid=d66bf8c2ea8b7cdd294cb97dbceefe1c")
    suspend fun getWeather(@Query("lat") latitude:Int, @Query("lon") longitude:Int): Response<WeatherResponse>
}