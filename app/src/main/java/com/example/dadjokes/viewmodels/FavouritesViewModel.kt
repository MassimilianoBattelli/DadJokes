package com.example.dadjokes.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dadjokes.models.JokeModel
import com.example.dadjokes.remote.models.Joke
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import com.example.dadjokes.data.JokeRepositoryInterface
import com.example.dadjokes.ui.JokeAdapterListener

class FavouritesViewModel(private val jokeRepository: JokeRepositoryInterface) : ViewModel(),
    JokeAdapterListener {
    val jokes: MutableLiveData<List<JokeModel>> by lazy {
        MutableLiveData<List<JokeModel>>()
    }

    fun getJokeList() {
        viewModelScope.launch {
            jokeRepository.fetchFavJokesFlow().collect { resultJokesFromRepo ->
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

    private fun JokeModel.toJoke(): Joke {
        return Joke(
            setup = this.setup,
            punchline = this.punchline,
            _id = this.id
        )
    }

    override suspend fun onFavouriteButtonClicked(jokeModel: JokeModel, isFavourite: Boolean) {
        if (isFavourite) {
            val joke = jokeModel.toJoke()
            jokeRepository.deleteFavourite(joke)
        }
        else {
            val joke = jokeModel.toJoke()
            jokeRepository.insertFavourite(joke)
        }
    }
    override suspend fun searchFavourite(joke: JokeModel): Boolean{
        return jokeRepository.searchFavourite(joke.id)
    }
}