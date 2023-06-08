package com.ayat.booklibraryapplication.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.ayat.booklibraryapplication.activity.BookActivity
import com.ayat.booklibraryapplication.R
import com.ayat.booklibraryapplication.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val sharedPref = requireActivity().getSharedPreferences("MyPref", MODE_PRIVATE)
        binding.btnSignup.setOnClickListener {
            if (binding.edtName.text.isNotEmpty() && binding.edtEmail.text.isNotEmpty() && binding.edtpassword.text.isNotEmpty() && binding.txtDate.text.isNotEmpty()) {
                val editor = sharedPref!!.edit()
                editor.putString("username", binding.edtName.text.toString().trim())
                editor.putString("email", binding.edtEmail.text.toString().trim())
                editor.putString("password", binding.edtpassword.text.toString().trim())
                editor.putString("Date", binding.txtDate.text.toString().trim())
                val b = editor.commit()
                if (b) {
//                    Log.e("TAG", "onCreateView: ${sharedPref.getString("username","")}")
                    Toast.makeText(requireActivity(), "Data Added Successfully", Toast.LENGTH_SHORT)
                        .show()
                    editor.putBoolean("login", true).apply()
                } else {
                    Toast.makeText(requireActivity(), "Failed Add", Toast.LENGTH_SHORT).show()
                }
                val i = Intent(requireActivity(), BookActivity::class.java)
                i.putExtra("Email", binding.edtEmail.text.toString())
                startActivity(i)
                requireActivity().finish()
            } else {
                Toast.makeText(requireActivity(), "Complete Data Entry", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnSignin.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, SignInFragment()).commit()
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


}