package com.example.alejofila.data.network

import com.example.alejofila.data.network.model.MovieResponse
import com.example.alejofila.data.network.model.PopularTvShowsResponse

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("tv/popular")
    fun getPopularTvShows(@Query("page") page: Int): Single<PopularTvShowsResponse>

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Single<MovieResponse>

    @GET("search/movie")
    fun getMovieByKeyowrd(@Query("page") page: Int,@Query("query") query: String):Single<MovieResponse>
}