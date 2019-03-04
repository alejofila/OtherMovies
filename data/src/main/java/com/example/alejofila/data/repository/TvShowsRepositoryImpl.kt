package com.example.alejofila.data.repository

import com.alejofila.domain.TvShowsRepository
import com.alejofila.domain.model.TvShow
import com.example.alejofila.data.mapper.TvShowMapper
import com.example.alejofila.data.network.TmdbApi
import io.reactivex.Single
import java.lang.IllegalStateException

class TvShowsRepositoryImpl(private val TmdbApi: TmdbApi) : TvShowsRepository {
    override fun getTopTvShows(page: Int): Single<List<TvShow>> {
        if(page <= 0){
            throw IllegalStateException("Can't query 'tv/popular' endoint with an invalid page, should be > 1, was $page")
        }
        return TmdbApi.getPopularTvShows(page)
            .map { it.results }
            .flattenAsObservable { it }
            .map { TvShowMapper.fromDataToDomain(it) }
            .toList()
    }

}