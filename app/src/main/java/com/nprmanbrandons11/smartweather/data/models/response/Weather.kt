package com.nprmanbrandons11.smartweather.data.models.response

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)