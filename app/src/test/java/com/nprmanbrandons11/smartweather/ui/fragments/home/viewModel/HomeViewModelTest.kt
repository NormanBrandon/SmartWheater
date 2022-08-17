package com.nprmanbrandons11.smartweather.ui.fragments.home.viewModel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nprmanbrandons11.smartweather.MainDispatcherRule
import com.nprmanbrandons11.smartweather.common.dataAccess.JSONFileLoader
import com.nprmanbrandons11.smartweather.data.models.WheatherInfo
import com.nprmanbrandons11.smartweather.domain.use_case.WeatherUsesCases
import com.nprmanbrandons11.smartweather.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    @Mock
    private lateinit var usesCases: WeatherUsesCases


    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)

        homeViewModel = HomeViewModel(usesCases)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun check_getWeather_responseNotNullOrEmpty() {

        val latitude = 19
        val longitude = -99
        runTest {
            val observer = Observer<List<WheatherInfo>>{}
            val response = JSONFileLoader().loadWeatherForecastResponse("getWeatherSuccessResponse")
            val outputRepository = flow {
                delay(10)
                emit(response)
            }
            Mockito.`when`(usesCases.getWeather(latitude,longitude)).thenReturn(outputRepository)

            try {
                homeViewModel.getResponse().observeForever(observer)
                homeViewModel.getWeather(latitude, longitude)
                val result = homeViewModel.getResponse().getOrAwaitValue {  }
                println(result)
                assertThat(result.isNullOrEmpty(), `is`(false))
            }
            finally {
                homeViewModel.getResponse().removeObserver(observer)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun check_getWeather_responseSizeIs6() {

        val latitude = 19
        val longitude = -99
        runBlocking {
            val observer = Observer<List<WheatherInfo>>{}
            val response = JSONFileLoader().loadWeatherForecastResponse("getWeatherSuccessResponse")
            println(response)
            val outputRepository = flow {
                delay(10)
                emit(response)
            }
            Mockito.`when`(usesCases.getWeather(latitude,longitude)).thenReturn(outputRepository)

            try {
                homeViewModel.getResponse().observeForever(observer)
                homeViewModel.getWeather(latitude, longitude)
                val result = homeViewModel.getResponse().getOrAwaitValue {  }
                println(result)
                assertThat(result.size, `is`(7))
            }
            finally {
                homeViewModel.getResponse().removeObserver(observer)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun check_getWeather_wrongLongitude_errorResponse() {

        val latitude = 19
        val longitude = -999
        runBlocking {
            val observer = Observer<List<WheatherInfo>>{}
            val response = JSONFileLoader().loadWeatherForecastResponse("getWeatherErrorResponse")
            //println(response)
            val outputRepository = flow {
                delay(10)
                emit(response)
            }
            Mockito.`when`(usesCases.getWeather(latitude,longitude)).thenReturn(outputRepository)

            try {
                homeViewModel.getResponse().observeForever(observer)
                homeViewModel.getWeather(latitude, longitude)
                val result = homeViewModel.getResponse().getOrAwaitValue {  }
                println(result)
                assertThat(result.isEmpty(), `is`(true))
            }
            finally {
                homeViewModel.getResponse().removeObserver(observer)
            }
        }
    }
}