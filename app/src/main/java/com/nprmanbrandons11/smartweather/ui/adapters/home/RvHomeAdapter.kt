package com.nprmanbrandons11.smartweather.ui.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.nprmanbrandons11.smartweather.R
import com.nprmanbrandons11.smartweather.data.models.WheatherInfo
import com.nprmanbrandons11.smartweather.databinding.ItemCardHomeBinding

class RvHomeAdapter(private val list: List<WheatherInfo>): RecyclerView.Adapter<RvHomeAdapter.CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding =ItemCardHomeBinding.inflate(from,parent,false)
        return CardViewHolder(binding, parent)

    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindCard(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class CardViewHolder(private val binding: ItemCardHomeBinding, private val parent: ViewGroup):RecyclerView.ViewHolder(binding.root) {

        fun bindCard(info: WheatherInfo){
                binding.weather.text = info.wheather
                binding.day.text = info.day.toString()
                binding.cardView.setOnClickListener {
                    Navigation.findNavController(parent).navigate(R.id.action_homeFragment_to_detailFragment)
                }
        }

    }
}