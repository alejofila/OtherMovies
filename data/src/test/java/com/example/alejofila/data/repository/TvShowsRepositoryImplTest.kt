package com.example.alejofila.data.repository

import com.example.alejofila.data.mapper.TvShowMapper
import com.example.alejofila.data.network.TmdbApi
import com.example.alejofila.data.network.model.PopularTvShowsResponse
import com.example.alejofila.data.network.model.TvShow
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalStateException


@RunWith(MockitoJUnitRunner::class)
class TvShowsRepositoryImplTest {


    lateinit var tvShowsRepositoryImpl: TvShowsRepositoryImpl

    @Mock
    lateinit var TmdbApi: TmdbApi

    @Before
    fun setUp() {

        tvShowsRepositoryImpl = TvShowsRepositoryImpl(TmdbApi)
    }

    @Test
    fun `send a valid page number and returns an empty list of movie shows`() {
        Mockito.`when`(TmdbApi.getPopularTvShows(1))
            .thenReturn(Single.just(PopularTvShowsResponse(1, 200, 300, emptyList())))

        tvShowsRepositoryImpl.getTopTvShows(1).test().assertValue { it.isEmpty() }
    }

    @Test(expected = IllegalStateException::class)
    fun `send an invalid page number and returns throws an exception`() {
        Mockito.`when`(TmdbApi.getPopularTvShows(-1))
            .thenReturn(Single.just(PopularTvShowsResponse(1, 200, 300, emptyList())))

        tvShowsRepositoryImpl.getTopTvShows(-1).test().assertValue { it.isEmpty() }
    }

    @Test
    fun `send a valid page number an returns list of movie shows`() {
        val name = "blablabla"
        val rating = 2.0
        val id = 0
        val overview = "lorem ipsum"
        val coverImage = "/asdasd123127"
        val tvShow = TvShow(name, rating, id, overview, coverImage)
        val tvShowDomain = TvShowMapper.fromDataToDomain(tvShow)
        Mockito.`when`(TmdbApi.getPopularTvShows(1)).thenReturn(
            Single.just(
                PopularTvShowsResponse(1, 200, 300, listOf(tvShow))
            )
        )
        tvShowsRepositoryImpl.getTopTvShows(1)
            .test()
            .assertValue {
                it[0]== tvShowDomain }

    }


}