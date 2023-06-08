package com.ayat.booklibraryapplication.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayat.booklibraryapplication.R
import com.ayat.booklibraryapplication.adapter.FavoriteAdapter
import com.ayat.booklibraryapplication.databinding.FragmentFavoriteBinding
import com.ayat.booklibraryapplication.db.DataAccess
import com.google.gson.Gson

class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding
    lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var dataAccess: DataAccess
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        dataAccess = DataAccess(requireContext())
        displayBook()
        return binding.root

    }

    private fun displayBook() {
        val data = dataAccess.getFavouriteBook()

        favoriteAdapter =
            FavoriteAdapter(requireActivity(), data, object : FavoriteAdapter.OnClickListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onFavoriteClick(position: Int) {
                    if (data[position].isFavorite == 0) {
                        data[position].isFavorite = 1
                        dataAccess.favoriteBook(data[position].id, 1)
                    } else {
                        data[position].isFavorite = 0
                        dataAccess.favoriteBook(data[position].id, 0)
                    }
                    displayBook()
                }

                override fun onItemClick(position: Int) {
                    val detailsBookFragment = DetailsBookFragment()
                    val arguments = Bundle()
                    arguments.putString("book", Gson().toJson(data[position]))
                    detailsBookFragment.arguments = arguments
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContainer, detailsBookFragment).addToBackStack(null)
                        .commit()
                }

            })
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFavorite.adapter = favoriteAdapter
    }
}