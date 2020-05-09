package com.example.myweather.data.source.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.myweather.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response


class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {


        if (!isNetworkAvailable()) {
            throw NoInternetException("Please Check Your Internet Connection")
        }
        return chain.proceed(chain.request())


    }

    private fun isNetworkAvailable(): Boolean {
        var result = false
        val connectivityManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }

            } else {

                val connectivityManager1 =
                    appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                connectivityManager1.activeNetworkInfo.also {networkInfo ->
                    result = networkInfo != null && networkInfo.isConnected


                }
            }
        }
        return result
    }

}