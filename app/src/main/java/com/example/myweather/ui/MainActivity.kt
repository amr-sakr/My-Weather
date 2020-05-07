package com.example.myweather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myweather.R
import com.example.myweather.networking.NetworkConnectionInterceptor
import com.example.myweather.networking.RequestInterceptor
import com.example.myweather.networking.SafeApiRequest
import com.example.myweather.networking.WebService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Just to make sure that Api call is working properly and will be removed
        GlobalScope.launch(Dispatchers.Main) {
            val networkConnectionInterceptor = NetworkConnectionInterceptor(this@MainActivity)
            val requestInterceptor = RequestInterceptor()

            try {
                val response = WebService(networkConnectionInterceptor, requestInterceptor).getCurrentWeather("New York")
                test_tv.text = response.body().toString()
                Toast.makeText(this@MainActivity, response.code().toString(), Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Timber.d("exception is ${e.message}")
                Timber.d("exception isss ${e.stackTrace}")
                Toast.makeText(this@MainActivity, e.message.toString(), Toast.LENGTH_SHORT).show()
                e.stackTrace
            }


        }
    }
}