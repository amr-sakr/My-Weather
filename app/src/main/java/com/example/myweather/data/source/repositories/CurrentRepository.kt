package com.example.myweather.data.source.repositories

import androidx.lifecycle.MutableLiveData
import com.example.myweather.data.Current
import com.example.myweather.data.source.remote.SafeApiRequest
import com.example.myweather.data.source.remote.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrentRepository(private val webService: WebService) : SafeApiRequest() {

    private val current = MutableLiveData<Current>()

    suspend fun getCurrentWeather(city: String): MutableLiveData<Current> {
        val response = apiRequest { webService.getCurrentWeather(city) }
        current.value = response.current
        current.postValue(current.value)
        return withContext(Dispatchers.Main) {
            current
        }
    }


}