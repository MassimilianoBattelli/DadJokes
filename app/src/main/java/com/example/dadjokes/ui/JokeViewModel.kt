package com.example.dadjokes

import androidx.lifecycle.*
import com.example.dadjokes.data.JokeRepository
import com.example.dadjokes.local.entities.JokeEntity
import kotlinx.coroutines.launch

class JokeViewModel(private val jokeRepository: JokeRepository) : ViewModel() {
    /*
    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allJokes: LiveData<List<JokeEntity>> = jokeRepository.allJokes.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(Joke: JokeEntity) = viewModelScope.launch {
        jokeRepository.insert(Joke)
    }
}

class JokeViewModelFactory(private val repository: JokeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JokeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    */

}