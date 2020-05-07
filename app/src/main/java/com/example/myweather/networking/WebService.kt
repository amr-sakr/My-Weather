package com.example.myweather.networking

import com.example.myweather.data.WeatherResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val BASE_URL = "http://api.weatherstack.com/"

@JvmSuppressWildcards
interface WebService {


    @GET("current")
    suspend fun getCurrentWeather(
        @Query("query") city: String
    ): Response<WeatherResponse>

    companion object {

        operator fun invoke(
            // passing networkConnectionInterceptor to the invoke as we cannot create
            // instance of NetworkConnectionInterceptor directly inside .addInterceptor() method
            networkConnectionInterceptor: NetworkConnectionInterceptor,
            //passing RequestInterceptor
            requestInterceptor: RequestInterceptor

        ): WebService {

            // adding interceptor to retrofit
            val okHttpClint = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClint)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebService::class.java)
        }
    }
}
