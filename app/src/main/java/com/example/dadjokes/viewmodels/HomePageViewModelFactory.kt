package com.example.dadjokes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dadjokes.data.JokeRepository
import com.example.dadjokes.data.JokeRepositoryInterface

class HomePageViewModelFactory(private val repository: JokeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomepageViewModel::class.java)) {
            return HomepageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}