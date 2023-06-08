package com.ayat.booklibraryapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayat.booklibraryapplication.R
import com.ayat.booklibraryapplication.databinding.ActivitySignInUpBinding
import com.ayat.booklibraryapplication.fragment.SignInFragment
import com.ayat.booklibraryapplication.fragment.SignUpFragment


class SignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivitySignInUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.container,SignInFragment()).commit()
        binding.btnSignin.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container,SignInFragment()).commit()
        }
        binding.btnSignup.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container,SignUpFragment()).commit()
        }


    }
}