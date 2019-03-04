package com.alejofila.domain

import com.alejofila.domain.model.Movie
import io.reactivex.Single

interface  MoviesRepository{
    fun getPopularMovies(page : Int) : Single<List<Movie>>
    fun getMovieByKeyword(page: Int, keyword:String) : Single<List<Movie>>
}