package com.example.alejofila.themovies.populartv.presenter

import com.alejofila.domain.MoviesRepository
import com.alejofila.domain.model.Movie
import com.alejofila.domain.usecase.GetPopularMoviesUseCaseImpl
import com.example.alejofila.themovies.common.mapper.MovieUiMapper
import com.example.alejofila.themovies.movies.presenter.PopularMoviesPresenter
import com.example.alejofila.themovies.movies.viewcontract.PopularMoviesView
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.internal.verification.VerificationModeFactory
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PopularMoviesPresenterTest {

    @Mock
    lateinit var view: PopularMoviesView

    @Mock
    lateinit var repository: MoviesRepository

    private val testScheduler = TestScheduler()
    private lateinit var presenter: PopularMoviesPresenter

    @Before
    fun setUp() {
        presenter = PopularMoviesPresenter(GetPopularMoviesUseCaseImpl(repository), testScheduler, testScheduler)
        presenter.view = view
    }

    @Test
    fun `when repository returns a non empty list should call showNextPageOfShows`() {
        val movie = Movie("", "basdasd ", "2019-20-30", "asdsad")
        val movies = listOf(movie)
        val singleMovies = Single.just(movies)
        Mockito.`when`(repository.getPopularMovies(1)).thenReturn(singleMovies)
        presenter.queryPopularMovies()
        testScheduler.triggerActions()
        Mockito.verify(view, VerificationModeFactory.times(1))
            ?.showNextPageOfMovies(listOf(MovieUiMapper.fromDomainToUiModel(movie)))
    }

    @Test
    fun `when repository returns an empty list should call showEmptyView`() {
        val singleEmptyMovies = Single.just(emptyList<Movie>())
        Mockito.`when`(repository.getPopularMovies(1)).thenReturn(singleEmptyMovies)
        presenter.queryPopularMovies()
        testScheduler.triggerActions()
        Mockito.verify(view, VerificationModeFactory.times(1))?.showEmptyView()
    }

    @Test
    fun `when page is more than total pages should call showNoMoreMoviesMessage`() {
        presenter.page = 812812
        presenter.queryPopularMovies()
        testScheduler.triggerActions()
        Mockito.verify(view, VerificationModeFactory.times(1))?.showNoMoreMoviesMessage()
    }

    @Test(expected = Exception::class)
    fun `when repository throws an exception should call showServerError`() {
        Mockito.`when`(repository.getPopularMovies(1)).thenThrow(Exception())
        testScheduler.triggerActions()
        Mockito.verify(view, VerificationModeFactory.times(1))?.showServerError()
    }
}