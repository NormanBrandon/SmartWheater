package com.nprmanbrandons11.smartweather.ui.fragments.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nprmanbrandons11.smartweather.data.models.WheatherInfo
import com.nprmanbrandons11.smartweather.domain.use_case.WeatherUsesCases
import kotlinx.coroutines.flow.onEach


class HomeViewModel: ViewModel() {
    private var weatherUsesCases = WeatherUsesCases()
    val _response = MutableLiveData<List<WheatherInfo>>(null)
    val response : LiveData<List<WheatherInfo>> = _response

    fun getWeather(latitude:Int,longitude:Int){
        weatherUsesCases.getWeather(latitude,longitude).onEach {result->
            if(result?.cod == "200"){
                val list: MutableList<WheatherInfo> = mutableListOf()
                for (i in 0 .. 6){
                    list.add(WheatherInfo(i,result.list[i].weather[0].main?:"not found"))
                }
                _response.value = list
            }
        }
    }

}