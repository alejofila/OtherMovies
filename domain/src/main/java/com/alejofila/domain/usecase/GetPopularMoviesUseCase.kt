package com.alejofila.domain.usecase

import com.alejofila.domain.MoviesRepository
import com.alejofila.domain.model.Movie
import io.reactivex.Single


interface GetPopularMoviesUseCase : GetMoviesUseCase
class GetPopularMoviesUseCaseImpl(private val repository: MoviesRepository) :
    GetPopularMoviesUseCase {
    override fun invoke(page: Int): Single<List<Movie>> {
        if(page <=0){
            throw  IllegalStateException("Can't execute this UseCase, page should be 1 or greater, current is :$page")
        }
        return repository.getPopularMovies(page)
    }

}