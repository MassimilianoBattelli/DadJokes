package com.example.dadjokes.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dadjokes.data.JokeRepositoryInterface
import com.example.dadjokes.models.JokeModel
import com.example.dadjokes.remote.models.Joke
import kotlinx.coroutines.launch

class HomeViewModel(private val jokeRepository: JokeRepositoryInterface) : ViewModel() {

    val jokes: MutableLiveData<List<JokeModel>> by lazy {
        MutableLiveData<List<JokeModel>>()
    }

    fun getJokeList() {
        viewModelScope.launch {
            jokeRepository.fetchJokesFlow().collect { resultJokesFromRepo ->
                val uiJokes = resultJokesFromRepo.map { it.toJokeModel() }
                jokes.postValue(uiJokes)
            }
        }
    }

    private fun Joke.toJokeModel(): JokeModel {
        return JokeModel(
            setup = this.setup,
            punchline = this.punchline,
            id = this._id
        )
    }
}
