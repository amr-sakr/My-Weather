package  com.example.myweather.data.source.remote


import com.example.myweather.utils.KEY
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val url = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("access_key", KEY)
            .build()

        request = request.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}