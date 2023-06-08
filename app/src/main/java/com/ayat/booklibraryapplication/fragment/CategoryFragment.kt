package com.ayat.booklibraryapplication.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayat.booklibraryapplication.R
import com.ayat.booklibraryapplication.activity.BookActivity
import com.ayat.booklibraryapplication.adapter.CategoryAdapter
import com.ayat.booklibraryapplication.databinding.FragmentCategoryBinding
import com.ayat.booklibraryapplication.db.DataAccess
import com.ayat.booklibraryapplication.model.Category
import com.google.gson.Gson

class CategoryFragment : Fragment() {
    lateinit var binding: FragmentCategoryBinding
    lateinit var categoryAdapter: CategoryAdapter
    private lateinit var dataAccess: DataAccess

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        if (arguments !== null)
            binding.txtTyp.text = requireArguments().getString("name")

        setHasOptionsMenu(true)
        dataAccess = DataAccess(requireContext())
        displayBook(dataAccess.getAllBook())
        binding.fAdd.setOnClickListener {
            val addBookFragment = AddBookFragment()
            val arguments = Bundle()
            arguments.putString("name", requireArguments().getString("name"))
            addBookFragment.arguments = arguments
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, addBookFragment).addToBackStack(null).commit()
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu, menu)
        val item = menu.findItem(R.id.search)
        val searchView =
            SearchView((requireContext() as BookActivity).supportActionBar!!.themedContext)
        searchView.isSubmitButtonEnabled = true
        searchView.maxWidth = Int.MAX_VALUE
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        item.actionView = searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.e("ayaddddt", query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty())
                    displayBook(dataAccess.searchBook(newText))
                return false
            }
        })

        searchView.setOnCloseListener {
            Log.e("ayddat", "Close")
            false
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun displayBook(data: ArrayList<Category>) {

        categoryAdapter =
            CategoryAdapter(requireActivity(), data, object : CategoryAdapter.OnClickListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onFavoriteClick(position: Int) {
                    if (data[position].isFavorite == 0) {
                        data[position].isFavorite = 1
                        dataAccess.favoriteBook(data[position].id, 1)
                    } else {
                        data[position].isFavorite = 0
                        dataAccess.favoriteBook(data[position].id, 0)
                    }
                    categoryAdapter.notifyItemChanged(position)
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

                override fun onItemDeleteClick(position: Int) {
                    val alertDialog = AlertDialog.Builder(requireContext())
                    alertDialog.setTitle("Delete Book")
                    alertDialog.setMessage("Are you sure to delete Book?")
                    alertDialog.setPositiveButton("yes") { _, _ ->
                        dataAccess.deleteBook(data[position].id)
                        if (dataAccess.deleteBook( position)){
                            data.removeAt(position)
                            notifyDataSetChanged()
                            Toast.makeText(requireActivity(),"Deleted Successfully", Toast.LENGTH_SHORT).show()
                        }
                        else
                            Toast.makeText(activity,"Delete Failed",Toast.LENGTH_SHORT).show()
                        //   categoryAdapter.notifyItemRemoved(position)
                        displayBook(data)
                    }
                    alertDialog.setNegativeButton("No") { _, _ ->
                    }
                    alertDialog.create().show()
                }
            })
        binding.rvCategory.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvCategory.adapter = categoryAdapter
    }

    private fun notifyDataSetChanged() {
        TODO("Not yet implemented")
    }
}



