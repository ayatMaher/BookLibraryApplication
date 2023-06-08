package com.ayat.booklibraryapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.ayat.booklibraryapplication.R

import com.ayat.booklibraryapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val splashScreen = 3000
    private lateinit var topAnimation: Animation
    private lateinit var bottomAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //hide status bar
        supportActionBar?.hide()
        //create Animation
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        binding.imgAnimation.animation = topAnimation
        binding.txtAnimation.animation = bottomAnimation
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed({
            val isLogin = sharedPref.getBoolean("login", false)
            if (isLogin){
                startActivity( Intent(this, BookActivity::class.java))
            }else{
                startActivity( Intent(this, SignActivity::class.java))
            }


        }, splashScreen.toLong())


    }

}