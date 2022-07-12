package com.nprmanbrandons11.smartweather.data.models

data class WheatherInfo(
    val day : Int,
    val wheather :String,
    val weather_desc :String,
    val date :String,
    val max_temp :Int,
    val min_temp : Int,
    val humidity :Int,
)
