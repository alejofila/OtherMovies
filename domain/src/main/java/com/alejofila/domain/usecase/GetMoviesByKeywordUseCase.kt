package com.alejofila.domain.usecase

import com.alejofila.domain.MoviesRepository
import com.alejofila.domain.model.Movie
import io.reactivex.Single


abstract class GetMoviesByKeywordUseCase : GetMoviesUseCase{
    var keyword: String = ""
}
class GetMoviesByKeywordUseCaseImpl(private val repository: MoviesRepository) : GetMoviesByKeywordUseCase(){
    override fun invoke(page: Int): Single<List<Movie>> {
        if(page <= 0){
            throw  IllegalStateException("Can't execute this usecase, page should be 1 or greater, current is :$page")

        }
        if(keyword.isEmpty()){
            throw  IllegalStateException("Can't execute this usecase with an empty String as keyword")
        }
        return repository.getMovieByKeyword(page,keyword)
    }

}