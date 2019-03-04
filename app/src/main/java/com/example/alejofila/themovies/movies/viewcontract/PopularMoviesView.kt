package com.example.alejofila.themovies.movies.viewcontract

import com.example.alejofila.themovies.common.uimodel.MoviesUiModel

interface PopularMoviesView{
    fun showNextPageOfMovies(movies: List<MoviesUiModel>)
    fun showNoMoreMoviesMessage()
    fun showServerError()
    fun showEmptyView()
    fun hideEmptyView()
    fun resetMovieList()
}