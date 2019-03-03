package com.example.alejofila.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


const val API_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "5d967c7c335764f39b1efbe9c5de9760"
const val BASE_URL_POSTER = "https://image.tmdb.org/t/p/w342"
const val LAST_PAGE = 8

object TvShowService {
    fun getTVShowsApi(): TvShowsApi {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()
        val retrofit = Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
        return retrofit.create(TvShowsApi::class.java)
    }

    fun getPosterPath(posterPath: String): String {
        return BASE_URL_POSTER + posterPath
    }
}
