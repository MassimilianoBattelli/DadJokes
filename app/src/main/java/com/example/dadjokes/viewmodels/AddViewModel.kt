package com.example.dadjokes.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dadjokes.models.JokeModel
import com.example.dadjokes.remote.models.Joke
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import com.example.dadjokes.data.JokeRepositoryInterface
import com.example.dadjokes.ui.JokeAdapterListener
import java.util.*

class AddViewModel(private val jokeRepository: JokeRepositoryInterface) : ViewModel() {
    val jokes: MutableLiveData<List<JokeModel>> by lazy {
        MutableLiveData<List<JokeModel>>()
    }

    suspend fun addToFavourites(setup: String, punchline: String) {
        val jokeModel = JokeModel(
            id = UUID.randomUUID().toString(), // The ID will be generated automatically, so set it to an empty string for now
            setup = setup,
            punchline = punchline
        )
        val joke = jokeModel.toJoke()
        jokeRepository.insertFavourite(joke)
    }

    private fun Joke.toJokeModel(): JokeModel {
        return JokeModel(
            setup = this.setup,
            punchline = this.punchline,
            id = this._id
        )
    }

    private fun JokeModel.toJoke(): Joke {
        return Joke(
            setup = this.setup,
            punchline = this.punchline,
            _id = this.id
        )
    }

}