package com.example.myweather.ui.today

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.data.Current
import com.example.myweather.data.source.repositories.CurrentRepository
import com.example.myweather.utils.ApiException
import com.example.myweather.utils.NoInternetException
import kotlinx.coroutines.launch
import timber.log.Timber

class CurrentViewModel(private val repository: CurrentRepository) : ViewModel() {
    private val _current = MutableLiveData<Current>()
    val current: LiveData<Current> get() = _current

    init {
        getCurrent()
    }

    private fun getCurrent() {
        viewModelScope.launch {
            //TODO handel loading
            try {
                val response = repository.getCurrentWeather("Alexandria, Egypt")
                response.value?.let {
                    _current.value = it
                }
            } catch (e: ApiException) {
                Timber.d("exception 0 -> ${e.message}")
            } catch (e: NoInternetException) {
                Timber.d("exception 1 -> ${e.message}")
            } catch (e: Exception) {
                Timber.d("exception 2 -> ${e.message}")
            }
        }
    }
}