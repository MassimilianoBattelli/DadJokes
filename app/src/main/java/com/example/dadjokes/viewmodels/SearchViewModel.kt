package com.example.dadjokes.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dadjokes.models.JokeModel
import com.example.dadjokes.remote.models.Joke
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import com.example.dadjokes.data.JokeRepositoryInterface
import com.example.dadjokes.ui.JokeAdapterListener

class SearchViewModel(private val jokeRepository: JokeRepositoryInterface) : ViewModel() {
    val jokes: MutableLiveData<List<JokeModel>> by lazy {
        MutableLiveData<List<JokeModel>>()
    }

    suspend fun searchJokeByKeyword(keyword: String): String{
        return jokeRepository.searchJokeByKeyword(keyword)
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