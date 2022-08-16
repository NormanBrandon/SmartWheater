package com.nprmanbrandons11.smartweather.data.remote.service

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nprmanbrandons11.smartweather.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.create


class WeatherServiceTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var service: WeatherApi

    companion object{
        private lateinit var retrofit: Retrofit
        @BeforeClass
        @JvmStatic
        fun setupCommon(){
            retrofit = com.nprmanbrandons11.smartweather.data.remote.Retrofit.getRetrofit()
        }
    }
    @Before
    fun setup(){
        service = retrofit.create(WeatherApi::class.java)
    }
    @Test
    fun checkCurrentWeatherIsNotNullTest(){
        runBlocking {
            val result = service.getWeather(19,-99)
            assertThat(result.body(), not(nullValue()))
        }
    }
    @Test
    fun checkCurrentWeatherCodeIsSuccessTest(){
        runBlocking {
            val result = service.getWeather(19,-99)
            assertThat(result.body()?.cod,`is`("200"))
        }
    }
}