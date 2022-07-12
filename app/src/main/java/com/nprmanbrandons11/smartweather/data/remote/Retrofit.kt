package com.nprmanbrandons11.smartweather.data.remote

import com.nprmanbrandons11.smartweather.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL_Services)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}