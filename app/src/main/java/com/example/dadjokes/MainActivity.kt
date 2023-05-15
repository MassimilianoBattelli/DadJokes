package com.example.dadjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes.models.JokeModel
import com.example.dadjokes.ui.JokeAdapter
import com.example.dadjokes.viewmodels.HomepageViewModel

class MainActivity : AppCompatActivity(){

    private val TAG = "MainActivity"

    private lateinit var viewModel: HomepageViewModel
    private lateinit var recyclerView: RecyclerView
    /*
    private val newJokeActivityRequestCode = 1
    private val jokeViewModel: JokeViewModel by viewModels {
        JokeViewModelFactory((application as JokesApplication).repository)
    }
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        /*
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
        */
        viewModel = ViewModelProvider(this)[HomepageViewModel::class.java]
        viewModel.jokes.observe(this) { jokes ->
            Log.d(TAG, "hotels $jokes")
            bindJokes(jokes)
        }
        viewModel.getJokeList()
    }
    /*
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

    override fun onLocationListRetrieved(list: Response<JokeResponseWrapper>) {
        dataSource = list.map {

        }
    }
    */

    private fun bindJokes(jokes: List<JokeModel>) {
        val adapter = JokeAdapter(jokes)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}