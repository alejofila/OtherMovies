package com.alejofila.domain.usecase

import com.alejofila.domain.MoviesRepository
import com.alejofila.domain.model.Movie
import io.reactivex.Single


abstract class GetMoviesByKeywordUseCase : GetMoviesUseCase{
    var keyword: String = ""
}
class GetMoviesByKeywordUseCaseImpl(private val repository: MoviesRepository) : GetMoviesByKeywordUseCase(){
    override fun invoke(page: Int): Single<List<Movie>> {
        return repository.getMovieByKeyword(page,keyword)
    }

}