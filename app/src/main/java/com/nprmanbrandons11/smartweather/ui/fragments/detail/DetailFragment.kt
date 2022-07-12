package com.nprmanbrandons11.smartweather.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nprmanbrandons11.smartweather.R
import com.nprmanbrandons11.smartweather.data.models.WheatherInfo
import com.nprmanbrandons11.smartweather.databinding.FragmentDetailBinding
import com.nprmanbrandons11.smartweather.databinding.FragmentHomeBinding


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private  var info : WheatherInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            info = it.getParcelable("weatherInfo")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.date.text = info?.date?.subSequence(0,10)
        binding.weather.text = info?.wheather
        binding.tempMax.text = info?.max_temp.toString() + "°F"
        binding.minTemp.text = info?.min_temp.toString() + "°F"
        binding.humudity.text = info?.humidity.toString()
        binding.btnOk.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}