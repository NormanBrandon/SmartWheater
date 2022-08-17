package com.nprmanbrandons11.smartweather.common.dataAccess

import com.google.gson.Gson
import com.nprmanbrandons11.smartweather.data.models.response.WeatherResponse
import java.io.InputStreamReader

class JSONFileLoader {
    private var jsonStr :String ? = null
    fun loadJSONString(file:String) :String?{
        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
        jsonStr = loader.readText()
        return jsonStr
    }
    fun loadWeatherForecastResponse(file:String):WeatherResponse?{
        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
        jsonStr = loader.readText()
        return Gson().fromJson(jsonStr,WeatherResponse::class.java)
    }

}