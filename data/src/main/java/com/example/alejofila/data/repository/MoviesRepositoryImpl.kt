package com.example.alejofila.data.repository

import com.alejofila.domain.MoviesRepository
import com.alejofila.domain.model.Movie
import com.example.alejofila.data.mapper.MovieMapper
import com.example.alejofila.data.network.TmdbApi
import io.reactivex.Single

class MoviesRepositoryImpl(private val tMdbApi: TmdbApi) : MoviesRepository {

    override fun getPopularMovies(page: Int): Single<List<Movie>> {
        if (page <= 0) {
            throw IllegalStateException("Can't query 'movie/popular' endpoint with an invalid page, should be > 1, was $page")
        }
        return tMdbApi.getPopularMovies(page)
            .map { it.results }
            .flattenAsObservable { it }
            .map { MovieMapper.fromDataToDomain(it) }
            .toList()
    }

    override fun getMovieByKeyword(page: Int, keyword: String): Single<List<Movie>> {
        if (page <= 0) {
            throw IllegalStateException("Can't query 'movie/popular' endpoint with an invalid page, should be > 1, was $page")
        }
        if (keyword.isEmpty()) {
            throw IllegalStateException("Can't query 'movie/popular' endpoint with a blank query")
        }
        return tMdbApi.getMovieByKeyowrd(page, keyword)
            .map { it.results }
            .flattenAsObservable { it }
            .map { MovieMapper.fromDataToDomain(it) }
            .toList()
    }


}