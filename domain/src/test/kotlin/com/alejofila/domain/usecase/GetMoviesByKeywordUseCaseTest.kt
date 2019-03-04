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
class GetMoviesByKeywordUseCaseTest {

    @Mock
    lateinit var repository: MoviesRepository
    lateinit var getMoviesByKeywordUseCase: GetMoviesByKeywordUseCase

    @Before
    fun setUp() {
        getMoviesByKeywordUseCase = GetMoviesByKeywordUseCaseImpl(repository)
    }

    @Test
    fun `when valid page and keyword should return  empty  list  of movies `(){
        val keyword = "blabla"
        Mockito.`when`(repository.getMovieByKeyword(1,keyword)).thenReturn(Single.just(emptyList()))
        getMoviesByKeywordUseCase.keyword = keyword
        val testObserver = getMoviesByKeywordUseCase(1).test()
        testObserver.assertValue{it.isEmpty()}
    }
    @Test(expected = IllegalStateException::class)
    fun `when page is 0 should throw an exception `(){
        val keyword = "blabla"
        val invalidPage = 0
        getMoviesByKeywordUseCase.keyword = keyword
        getMoviesByKeywordUseCase(invalidPage).test()
    }
    @Test(expected = IllegalStateException::class)
    fun `when empty keyword is used should throw Exception`(){
        val keyword = ""

        getMoviesByKeywordUseCase.keyword = keyword
        val testObserver = getMoviesByKeywordUseCase(1).test()
        testObserver.assertValue{it.isEmpty()}
    }
    @Test
    fun `when valid page and keyboard should return list of multiple movies`() {
        val movie = Movie("blabla","blablaba","blablabla","asdasdasd")
        val movie2 =  Movie("blabla","blablaba","blablabla","asdasdasd")
        val list = listOf(movie, movie2)
        val page =1
        val keyword = "abcde"
        getMoviesByKeywordUseCase.keyword = keyword
        Mockito.`when`(repository.getMovieByKeyword(page,keyword)).thenReturn(Single.just(list))
        val testObserver = getMoviesByKeywordUseCase(page).test()
        testObserver.assertValue{it==list}

    }
}