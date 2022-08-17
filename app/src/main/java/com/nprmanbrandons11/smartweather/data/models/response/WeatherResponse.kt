package com.nprmanbrandons11.smartweather.data.models.response

data class WeatherResponse(
    val city: City?,
    val cnt: Int?,
    val cod: String?,
    val list: List<Child?>?,
    val message: Any?
)