package com.example.dadjokes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes.local.entities.JokeEntity
import com.example.dadjokes.ui.JokeListAdapter
import com.example.dadjokes.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val newJokeActivityRequestCode = 1
    private val jokeViewModel: JokeViewModel by viewModels {
        JokeViewModelFactory((application as JokesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = JokeListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        jokeViewModel.allJokes.observe(this, Observer { jokes ->
            // Update the cached copy of the words in the adapter.
            jokes?.let { adapter.submitList(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewJokeActivity::class.java)
            startActivityForResult(intent, newJokeActivityRequestCode)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newJokeActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewJokeActivity.EXTRA_REPLY)?.let {
                val joke = JokeEntity(it)
                jokeViewModel.insert(joke)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}