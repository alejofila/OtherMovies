package com.example.alejofila.data.repository


import com.example.alejofila.data.mapper.MovieMapper
import com.example.alejofila.data.network.TmdbApi
import com.example.alejofila.data.network.model.MovieEntity
import com.example.alejofila.data.network.model.MovieResponse
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImplTest {


    lateinit var moviesRepository: MoviesRepositoryImpl

    @Mock
    lateinit var tmdbApi: TmdbApi

    @Before
    fun setUp() {
        moviesRepository = MoviesRepositoryImpl(tmdbApi)
    }

    @Test
    fun `send a valid page number and returns an empty list of movies`() {
        Mockito.`when`(tmdbApi.getPopularMovies(1))
            .thenReturn(Single.just(MovieResponse(1, 200, 300, emptyList())))

        moviesRepository.getPopularMovies(1).test().assertValue { it.isEmpty() }
    }

    @Test(expected = IllegalStateException::class)
    fun `send an invalid page number and returns throws an exception`() {
        moviesRepository.getPopularMovies(-1).test().assertValue { it.isEmpty() }
    }

    @Test
    fun `send a valid page number an returns list of movies`() {

        val movie = MovieEntity("", "abc", "abc", "adad")
        val movieDomain = MovieMapper.fromDataToDomain(movie)
        Mockito.`when`(tmdbApi.getPopularMovies(1)).thenReturn(
            Single.just(
                MovieResponse(1, 200, 300, listOf(movie))
            )
        )
        moviesRepository.getPopularMovies(1)
            .test()
            .assertValue {
                it[0] == movieDomain
            }

    }


}