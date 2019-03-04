package com.alejofila.domain.usecase

import com.alejofila.domain.MoviesRepository
import com.alejofila.domain.model.Movie
import io.reactivex.Single

interface GetPopularMoviesUseCase : GetMoviesUseCase

class GetPopularMoviesUseCaseImpl(private val repository: MoviesRepository) : GetPopularMoviesUseCase{
    override fun invoke(page: Int): Single<List<Movie>> {
        return repository.getPopularMovies(page)
    }

}

/**
 * Marker interface
 */
interface GetMoviesUseCase{
    operator fun invoke(page: Int): Single<List<Movie>>
}