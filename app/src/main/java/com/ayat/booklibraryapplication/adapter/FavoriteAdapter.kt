package com.ayat.booklibraryapplication.adapter

import android.app.Activity
import android.app.AlertDialog
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ayat.booklibraryapplication.R
import com.ayat.booklibraryapplication.databinding.CategoryItemBinding
import com.ayat.booklibraryapplication.model.Category
import java.lang.Exception

class FavoriteAdapter(
    var activity: Activity,
    var data: ArrayList<Category>,
    private var listener: OnClickListener
) :
    RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {
    class MyViewHolder(var binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = CategoryItemBinding.inflate(activity.layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        try {
            holder.binding.imgBook.setImageURI(Uri.parse(data[position].img))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        holder.binding.txtName.text = data[position].name
        holder.binding.txtAuthor.text = data[position].author

        if (data[position].isFavorite == 1) {
            holder.binding.imgFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    activity,
                    R.drawable.ic_full_favorite
                )
            )
        } else {
            holder.binding.imgFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    activity,
                    R.drawable.ic_favorite
                )
            )
        }

        holder.binding.imgDelete.visibility = View.GONE

        holder.binding.imgFavorite.setOnClickListener {
            listener.onFavoriteClick(position)
        }
        holder.binding.constraint.setOnClickListener { listener.onItemClick(position) }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnClickListener {
        fun onFavoriteClick(position: Int)
        fun onItemClick(position: Int)
    }
}
