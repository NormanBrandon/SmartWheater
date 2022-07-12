package com.nprmanbrandons11.smartweather.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.nprmanbrandons11.smartweather.databinding.FragmentHomeBinding
import com.nprmanbrandons11.smartweather.ui.adapters.home.RvHomeAdapter
import com.nprmanbrandons11.smartweather.ui.fragments.home.viewModel.HomeViewModel
import com.nprmanbrandons11.smartweather.utils.NetworkUtils
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var adapter: RvHomeAdapter
    private val  viewModel : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkNetwork()


    }
    private fun checkNetwork() {
        val networkConnection = NetworkUtils(requireContext())
        networkConnection.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                binding.network.visibility = View.GONE
                binding.rvHome.visibility = View.VISIBLE
                networkConnection.removeObservers(viewLifecycleOwner)
                viewModel.getWeather(35, 139)
                viewModel.response.observe(viewLifecycleOwner){
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


}