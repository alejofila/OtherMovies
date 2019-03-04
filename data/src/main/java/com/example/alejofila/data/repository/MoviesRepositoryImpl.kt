package com.example.alejofila.data.repository

import com.alejofila.domain.MoviesRepository
import com.alejofila.domain.model.Movie
import com.alejofila.domain.model.TvShow
import com.example.alejofila.data.mapper.MovieMapper
import com.example.alejofila.data.mapper.TvShowMapper
import com.example.alejofila.data.network.TmdbApi
import io.reactivex.Single
import java.lang.IllegalStateException

class MoviesRepositoryImpl(val tMdbApi: TmdbApi) : MoviesRepository {

    override fun getPopularMovies(page: Int): Single<List<Movie>> {
        if (page <= 0) {
            throw IllegalStateException("Can't query 'movie/popular' endpoint with an invalid page, should be > 1, was $page")
        }
        return tMdbApi.getPopularMovies(page)
            .map { it.result }
            .flattenAsObservable { it }
            .map { MovieMapper.fromDataToDomain(it) }
            .toList()
    }

    override fun getMovieByKeyword(keyword: String, page: Int): Single<List<Movie>> {
        if (page <= 0) {
            throw IllegalStateException("Can't query 'movie/popular' endpoint with an invalid page, should be > 1, was $page")
        }
        if (keyword.isEmpty()) {
            throw IllegalStateException("Can't query 'movie/popular' endpoint with a blank query")
        }
        return tMdbApi.getMovieByKeyowrd(page, keyword)
            .map { it.result }
            .flattenAsObservable { it }
            .map { MovieMapper.fromDataToDomain(it) }
            .toList()
    }


}