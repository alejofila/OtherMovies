package com.alejofila.domain.usecase

import com.alejofila.domain.TvShowsRepository
import com.alejofila.domain.model.Movie
import com.alejofila.domain.model.TvShow
import io.reactivex.Single

interface GetPopularMoviesUseCase{
    operator fun invoke(page: Int) : Single<List<Movie>>
}
class GetPopularMoviesUseCaseImpl(private val repository: TvShowsRepository) : GetPopularTvShowsUseCase{
    override fun invoke(page: Int): Single<List<TvShow>> {
        return repository.getTopTvShows(page)
    }

}