package com.example.myweather.ui.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myweather.data.source.repositories.CurrentRepository

@Suppress("UNCHECKED_CAST")
class CurrentViewModelFactory(private val repository: CurrentRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentViewModel(repository) as T
    }
}