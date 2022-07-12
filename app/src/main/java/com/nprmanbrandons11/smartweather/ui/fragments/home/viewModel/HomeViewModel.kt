package com.nprmanbrandons11.smartweather.ui.fragments.home.viewModel

import androidx.lifecycle.ViewModel
import com.nprmanbrandons11.smartweather.data.remote.Retrofit

class HomeViewModel: ViewModel() {
    private val retrofit = Retrofit.getRetrofit()


}