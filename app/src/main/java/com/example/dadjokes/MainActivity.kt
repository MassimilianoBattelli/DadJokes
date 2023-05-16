package com.example.dadjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes.fragments.AppbarFragment
import com.example.dadjokes.fragments.RecyclerFragment
import com.example.dadjokes.models.JokeModel
import com.example.dadjokes.ui.JokeAdapter
import com.example.dadjokes.viewmodels.HomePageViewModelFactory
import com.example.dadjokes.viewmodels.HomepageViewModel

class MainActivity : AppCompatActivity(){

    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = AppbarFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_appbar, fragment)
            .commit()

        val fragment2 = RecyclerFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, fragment2)
            .commit()

    }
}