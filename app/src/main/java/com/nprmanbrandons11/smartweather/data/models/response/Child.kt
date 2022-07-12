package com.nprmanbrandons11.smartweather.data.models.response

import com.google.gson.annotations.SerializedName


data class Child(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)