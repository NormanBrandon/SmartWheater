package com.nprmanbrandons11.smartweather.ui.fragments.home

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.nprmanbrandons11.smartweather.databinding.FragmentHomeBinding
import com.nprmanbrandons11.smartweather.ui.adapters.home.RvHomeAdapter
import com.nprmanbrandons11.smartweather.ui.fragments.home.viewModel.HomeViewModel
import com.nprmanbrandons11.smartweather.utils.NetworkUtils
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var adapter: RvHomeAdapter
    private val  viewModel : HomeViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        checkPermisions()



    }
    private fun checkNetwork(latitude:Int,longitude:Int) {
        val networkConnection = NetworkUtils(requireContext())
        networkConnection.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                binding.network.visibility = View.GONE
                binding.rvHome.visibility = View.VISIBLE
                networkConnection.removeObservers(viewLifecycleOwner)
                viewModel.getWeather(latitude, longitude)
                viewModel.getResponse().observe(viewLifecycleOwner){
                    adapter = RvHomeAdapter(it)
                    binding.rvHome.layoutManager = LinearLayoutManager(requireContext(),VERTICAL, false)
                    binding.rvHome.adapter = adapter

                }
            } else {
                binding.network.visibility = View.VISIBLE
                binding.rvHome.visibility = View.GONE
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun checkPermisions(){
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {

                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    if (ActivityCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        fusedLocationClient.lastLocation
                            .addOnSuccessListener { location : Location? ->
                                if(location?.latitude !=null)
                                    checkNetwork(location.latitude.toInt(),location.longitude.toInt())
                                else
                                    Toast.makeText(requireContext(),"We cant find your location",Toast.LENGTH_SHORT).show()
                            }
                    }

                } else -> {
                Toast.makeText(requireContext(),"You need to accept gps permissions to continue",Toast.LENGTH_SHORT).show()
            }
            }
        }
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION))
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    if(location?.latitude !=null)
                        checkNetwork(location.latitude.toInt(),location.longitude.toInt())
                    else
                        Toast.makeText(requireContext(),"We cant find your location",Toast.LENGTH_SHORT).show()
                }
        }
    }




}