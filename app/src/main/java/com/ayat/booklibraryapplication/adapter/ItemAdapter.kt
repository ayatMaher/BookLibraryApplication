package com.ayat.booklibraryapplication.adapter

import android.app.Activity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ayat.booklibraryapplication.databinding.ItemBinding

import com.ayat.booklibraryapplication.model.Item

class ItemAdapter(var activity: Activity, var arr:ArrayList<Item>):RecyclerView.Adapter<ItemAdapter.MainViewHolder>() {
    class MainViewHolder(var binding:ItemBinding):RecyclerView.ViewHolder(binding.root){
        var itemImage:ImageView
        var itemName:TextView
        init {
            itemImage=binding.imgType
            itemName= binding.txtName
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding= ItemBinding.inflate(activity.layoutInflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.imgType.setImageResource(arr[position].img)
        holder.binding.txtName.text=arr[position].name
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}