package com.example.dadjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes.fragments.AppbarFragment
import com.example.dadjokes.models.JokeModel
import com.example.dadjokes.ui.JokeAdapter
import com.example.dadjokes.viewmodels.HomePageViewModelFactory
import com.example.dadjokes.viewmodels.HomepageViewModel

class MainActivity : AppCompatActivity(){

    private val TAG = "MainActivity"

    private lateinit var viewModel: HomepageViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = AppbarFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_appbar, fragment)
            .commit()


        recyclerView = findViewById(R.id.recyclerview)

        viewModel = ViewModelProvider(this, HomePageViewModelFactory((application as JokesApplication).repository))[HomepageViewModel::class.java]
        //viewModel = ViewModelProvider(this)[HomepageViewModel::class.java]
        viewModel.jokes.observe(this) { jokes ->
            Log.d(TAG, "jokes $jokes")
            bindJokes(jokes)
        }
        viewModel.getJokeList()
    }

    private fun bindJokes(jokes: List<JokeModel>) {
        val adapter = JokeAdapter(jokes)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}