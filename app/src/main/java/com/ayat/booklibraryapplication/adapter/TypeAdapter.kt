package com.ayat.booklibraryapplication.adapter

import android.app.Activity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayat.booklibraryapplication.databinding.TypeItemBinding
import com.ayat.booklibraryapplication.model.Item
import com.ayat.booklibraryapplication.model.Type

class TypeAdapter(
    var activity: Activity,
    var data: ArrayList<Type>,
    var listener: OnClickListener
) : RecyclerView.Adapter<TypeAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: TypeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var typeTitle: TextView
        lateinit var itemRecycler: RecyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = TypeItemBinding.inflate(activity.layoutInflater, parent, false)
        val data = ArrayList<TypeAdapter>()
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.txtType.text = data[position].typeName
        setItemRecycler(holder.binding.rvTypeItem, data[position].item)
        holder.binding.btnView.setOnClickListener {
            listener.onClick(position)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun setItemRecycler(recyclerView: RecyclerView, item: ArrayList<Item>) {
        val itemAdapter = ItemAdapter(activity, item)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = itemAdapter
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }
}