package com.ayat.booklibraryapplication.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ayat.booklibraryapplication.R
import com.ayat.booklibraryapplication.databinding.FragmentAddBookBinding
import com.ayat.booklibraryapplication.db.DataAccess
import com.github.dhaval2404.imagepicker.ImagePicker
import java.lang.Exception


class AddBookFragment : Fragment() {
    lateinit var binding: FragmentAddBookBinding
    private lateinit var dataAccess: DataAccess
    private var mProfileUri: Uri? = null

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBookBinding.inflate(inflater, container, false)
        dataAccess = DataAccess(requireContext())
        //image Picker
        binding.imgB.setOnClickListener {
            imagePicker()
        }

        binding.btnSave.setOnClickListener {
            saveData()
        }

        return binding.root
    }

    private fun saveData() {
        try {
            val bookName = binding.edtName.text.toString().trim()
            val bookAuther = binding.edtAuthor.text.toString().trim()
            val bookShelf = binding.edtShelf.text.toString().trim()
            val bookNaYear = binding.edtYear.text.toString().trim()
            val bookCategory = binding.edtCategory.text.toString().trim()
            val bookLang = binding.edtlang.text.toString().trim()
            val bookNumber = binding.edtnumber.text.toString().trim()
            val bookCopies = binding.edtCopies.text.toString().trim()
            val bookDescription = binding.edtDescription.text.toString().trim()
            if (mProfileUri == null) return
            if (bookName.isEmpty()) return
            if (bookAuther.isEmpty()) return
            if (bookShelf.isEmpty()) return
            if (bookNaYear.isEmpty()) return
            if (bookCategory.isEmpty()) return
            if (bookLang.isEmpty()) return
            if (bookNumber.isEmpty()) return
            if (bookCopies.isEmpty()) return
            if (bookDescription.isEmpty()) return
            Log.e("TAG", "saveData: $mProfileUri  ${mProfileUri!!.path}")
            dataAccess.insertBook(
                bookName,
                bookAuther,
                bookNaYear.toInt(),
                bookShelf,
                bookCategory,
                bookLang,
                bookNumber.toInt(),
                bookCopies.toInt(),
                bookDescription,
                "$mProfileUri"
            )
            Toast.makeText(requireContext(), "Adding Successfully", Toast.LENGTH_LONG).show()
            val categoryFragment = CategoryFragment()
            val arguments = Bundle()
            arguments.putString("name", requireArguments().getString("name"))
            categoryFragment.arguments = arguments
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, categoryFragment).addToBackStack(null).commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("WrongConstant")
    private fun imagePicker() {
        ImagePicker.with(requireActivity())
            .compress(1024)         //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            ).start { resultCode, data ->
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        //Image Uri will not be null for RESULT_OK
                        val fileUri = data?.data!!

                        mProfileUri = fileUri
                        binding.imgB.setImageURI(fileUri)
                        Log.e("TAG", "saveData: $mProfileUri  ${mProfileUri!!.path}")
                    }
                    ImagePicker.RESULT_ERROR -> {
                        Toast.makeText(
                            requireContext(),
                            ImagePicker.getError(data),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
    }
}