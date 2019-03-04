package com.alejofila.domain.usecase

import com.alejofila.domain.MoviesRepository
import com.alejofila.domain.model.Movie
import io.reactivex.Single


interface GetPopularMoviesUseCase{
    operator fun invoke(page: Int) : Single<List<Movie>>
}
class GetPopularTvShowsUseCaseImpl(private val repository: MoviesRepository) :
    GetPopularMoviesUseCase {
    override fun invoke(page: Int): Single<List<Movie>> {
        return repository.getPopularMovies(page)
    }

}