package com.ayat.booklibraryapplication.activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.ayat.booklibraryapplication.R
import com.ayat.booklibraryapplication.fragment.*
import com.ayat.booklibraryapplication.databinding.ActivityBookBinding

class BookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, TypeFragment())
            .commit()
        supportFragmentManager.beginTransaction()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContainer, ProfileFragment()).addToBackStack(null).commit()
            }
            R.id.favorite -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContainer, FavoriteFragment()).addToBackStack(null).commit()
            }
            R.id.signout -> {
                val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
                sharedPref.edit().putBoolean("login", false).apply()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }


        return super.onOptionsItemSelected(item)
    }


}