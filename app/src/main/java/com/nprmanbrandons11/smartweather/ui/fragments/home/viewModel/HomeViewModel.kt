package com.nprmanbrandons11.smartweather.ui.fragments.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nprmanbrandons11.smartweather.data.models.WheatherInfo
import com.nprmanbrandons11.smartweather.domain.use_case.WeatherUsesCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class HomeViewModel(
    private val weatherUsesCases:WeatherUsesCases = WeatherUsesCases()
): ViewModel() {

    private val _response = MutableLiveData<List<WheatherInfo>>(listOf())
    fun getResponse():LiveData<List<WheatherInfo>> = _response
    fun getWeather(latitude:Int,longitude:Int){
        weatherUsesCases.getWeather(latitude,longitude).onEach {result->
            println(result)
            if(result?.cod == "200"){
                val list: MutableList<WheatherInfo> = mutableListOf()
                for (i in 0 .. 6){
                    list.add(
                        WheatherInfo(
                            i+1,
                            result.list?.get(i)?.weather?.get(0)?.main,
                            result.list?.get(i)?.weather?.get(0)?.description,
                            result.list?.get(i)?.dt_txt,
                            result.list?.get(i)?.main!!.temp_max,
                            result.list?.get(i)?.main!!.temp_min,
                            result.list?.get(i)?.main!!.humidity
                        ))
                }
                _response.value = list
                //_response.postValue(list)
            }
            else{
                _response.value = listOf()
                //hay que mockear los Log para que funcionen bien los test
                Log.d("Error de servicio","Error")
            }

        }.launchIn(viewModelScope)
    }

}