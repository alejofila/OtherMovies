package com.example.alejofila.themovies.common.mapper

import com.alejofila.domain.model.Movie
import com.example.alejofila.themovies.common.uimodel.MoviesUiModel

object MovieUiMapper {
    fun fromDomainToUiModel(movie: Movie): MoviesUiModel {
        with(movie) {
            val releaseYear = releaseDate.split("-").get(0)
            return MoviesUiModel(
                title,
                overview,
                releaseYear,
                posterImage
            )
        }
    }
}