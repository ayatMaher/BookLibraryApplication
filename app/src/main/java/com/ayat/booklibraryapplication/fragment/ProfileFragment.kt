package com.ayat.booklibraryapplication.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.ayat.booklibraryapplication.databinding.FragmentProfileBinding
import com.github.dhaval2404.imagepicker.ImagePicker

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    private var mProfileUri: Uri? = null
    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        // sharedPref
        var sharedPref =
            requireActivity().getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)
        val userName = sharedPref.getString("username", "")
        binding.edtName.setText(userName)
        val email = sharedPref.getString("email", "")
        binding.edtEmail.setText(email)
        val password = sharedPref.getString("password", "")
        binding.edtpassword.setText(password)
        val date = sharedPref.getString("Date", "")
        binding.txtDate.setText(date)
//        Log.e("TAG", "onCreateView:${sharedPref.getString("username", "")} ")

//        binding.btnSave.setOnClickListener {
//            sharedPref =
//                requireActivity().getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)
//            val editor = sharedPref!!.edit()
//            editor.putString("username", binding.edtName.text.toString().trim())
//            editor.putString("email", binding.edtEmail.text.toString().trim())
//            editor.putString("password", binding.edtpassword.text.toString().trim())
//            editor.putString("Date", binding.txtDate.text.toString().trim())
//            Toast.makeText(requireContext(),"srgwh",Toast.LENGTH_SHORT).show()
//        }
// image Picker
        binding.imgPerson.setOnClickListener {
            imagePicker()
        }
        // Date Picker
        binding.txtDate.setOnClickListener {
            val currentDate = Calendar.getInstance()
            val day = currentDate.get(Calendar.DAY_OF_MONTH)
            val month = currentDate.get(Calendar.MONTH)
            val year = currentDate.get(Calendar.YEAR)
            val picker = DatePickerDialog(
                requireActivity(),
                { datePicker, y, m, d ->
                    binding.txtDate.text = "$y / ${m + 1} / $d"
                }, year, month, day
            )
            picker.show()
        }


        return binding.root
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
                        binding.imgPerson.setImageURI(fileUri)
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