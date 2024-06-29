package com.example.weathertomorrow.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertomorrow.databinding.ItemResultBinding
import com.example.weathertomorrow.models.responses.get.WeatherResponse

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    private val list = mutableListOf<WeatherResponse>()
    private var lastSelectedItemPosition = RecyclerView.NO_POSITION


    inner class ResultViewHolder(val itemBinding: ItemResultBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val item = list[position]
        holder.itemBinding.item = item


    }



    fun updateList(newList: List<WeatherResponse>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun resetSelectedItemPosition() {
        lastSelectedItemPosition = RecyclerView.NO_POSITION
        notifyDataSetChanged()
    }
}