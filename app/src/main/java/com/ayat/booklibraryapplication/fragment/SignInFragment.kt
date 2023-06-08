package com.ayat.booklibraryapplication.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ayat.booklibraryapplication.activity.BookActivity
import com.ayat.booklibraryapplication.R
import com.ayat.booklibraryapplication.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    lateinit var binding: FragmentSignInBinding
    private lateinit var sharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        sharedPref =
            requireActivity().getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)

        binding.btnSignin.setOnClickListener {
            signIn()
        }
        binding.btnSignup.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, SignUpFragment()).commit()
        }

        return binding.root
    }

    private fun signIn() {
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtpassword.text.toString().trim()

        if (email.isEmpty()) {
            Toast.makeText(requireActivity(), "Email is required", Toast.LENGTH_SHORT).show()
            return
        }
        if (email != sharedPref.getString("email", "")) {
            Toast.makeText(requireActivity(), "Email is wrong", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.isEmpty()) {
            Toast.makeText(requireActivity(), "Password is required", Toast.LENGTH_SHORT).show()
            return
        }
        if (password != sharedPref.getString("password", "")) {
            Toast.makeText(requireActivity(), "Password is wrong", Toast.LENGTH_SHORT).show()
            return
        }

        val i = Intent(requireActivity(), BookActivity::class.java)
        startActivity(i)
        sharedPref.edit().putBoolean("login", true).apply()
    }
}