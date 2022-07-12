package com.nprmanbrandons11.smartweather.data.remote.service

import com.nprmanbrandons11.smartweather.data.models.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApi {
    @GET("forecast?lat={lat}&lon={lon}&appid=d66bf8c2ea8b7cdd294cb97dbceefe1c")
    suspend fun getWeather(@Path("lat") latitude:Int,@Path("lon") longitude:Int): Response<WeatherResponse>
}