package com.alejofila.domain.usecase

import com.alejofila.domain.MoviesRepository
import com.alejofila.domain.model.Movie
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUseCaseTest {

    @Mock
    lateinit var repository: MoviesRepository
    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Before
    fun setUp() {
        getPopularMoviesUseCase = GetPopularTvShowsUseCaseImpl(repository)
    }

    @Test
    fun `when valid page should return  empty  list  of movies `(){
        Mockito.`when`(repository.getPopularMovies(1)).thenReturn(Single.just(emptyList()))
        val testObserver = getPopularMoviesUseCase(1).test()
        testObserver.assertValue{it.isEmpty()}
    }

    @Test(expected = IllegalStateException::class)
    fun `when page is 0 should throw an exception `(){
        val invalidPage = 0
        getPopularMoviesUseCase
        getPopularMoviesUseCase(invalidPage).test()
    }

    @Test
    fun `when valid page should return list of multiple movies`() {
        val movie = Movie("blabla","blablaba","blablabla","asdasdasd")
        val movie2 =  Movie("blabla","blablaba","blablabla","asdasdasd")
        val list = listOf(movie, movie2)
        val page =1
        Mockito.`when`(repository.getPopularMovies(page)).thenReturn(Single.just(list))
        val testObserver = getPopularMoviesUseCase(page).test()
        testObserver.assertValue{it==list}
    }

}