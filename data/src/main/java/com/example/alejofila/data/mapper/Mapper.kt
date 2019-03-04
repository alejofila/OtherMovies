package com.example.alejofila.data.mapper

import com.alejofila.domain.model.Movie
import com.example.alejofila.data.network.model.MovieEntity

object MovieMapper {
    fun fromDataToDomain(movieData: MovieEntity): Movie {
        with(movieData) {
            return Movie(
                title,
                overview,
                releaseDate,
                posterImage
            )

        }
    }
}

