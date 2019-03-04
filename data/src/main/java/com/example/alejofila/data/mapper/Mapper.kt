package com.example.alejofila.data.mapper

import com.alejofila.domain.model.Movie
import com.example.alejofila.data.network.model.MovieEntity
import com.example.alejofila.data.network.model.TvShow

object TvShowMapper {
    fun fromDataToDomain(tvShowData: TvShow): com.alejofila.domain.model.TvShow {
        with(tvShowData) {
            return com.alejofila.domain.model.TvShow(
                name,
                rating,
                id,
                overview,
                coverImage
            )

        }
    }
}
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

