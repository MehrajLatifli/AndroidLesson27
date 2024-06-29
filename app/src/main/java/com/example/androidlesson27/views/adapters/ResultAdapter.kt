package com.example.androidlesson27.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlesson27.R
import com.example.androidlesson27.databinding.ItemResultBinding
import com.example.androidlesson27.models.responses.get.WeatherResponse
import com.example.androidlesson27.utilities.loadImageWithGlideAndResize

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