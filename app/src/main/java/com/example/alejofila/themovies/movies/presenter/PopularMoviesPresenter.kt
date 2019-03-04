package com.example.alejofila.themovies.movies.presenter

import android.util.Log
import com.alejofila.domain.usecase.GetPopularMoviesUseCase

import com.alejofila.newsdemo.common.presenter.BasePresenter
import com.example.alejofila.data.network.LAST_PAGE
import com.example.alejofila.themovies.common.mapper.MovieUiMapper
import com.example.alejofila.themovies.movies.viewcontract.PopularMoviesView
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.subscribeBy

class PopularMoviesPresenter(
    private val useCase: GetPopularMoviesUseCase,
    mainScheduler: Scheduler,
    backgroundScheduler: Scheduler
) :
    BasePresenter(mainScheduler = mainScheduler, backgroundScheduler = backgroundScheduler) {
    lateinit var view: PopularMoviesView
    var page = 1
    override fun onStart() {

    }

    fun queryPopularMovies() {
        if (morePages()) {
            disposableBag.add(useCase(page)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .flattenAsObservable { it }
                .map { MovieUiMapper.fromDomainToUiModel(it) }
                .toList()
                .subscribeBy(
                    onError = {
                        Log.e("Tag", "Error in presenter onSubscribe $it.message")
                        view.showServerError()
                        view.showEmptyView()
                    },
                    onSuccess = {
                        if (it.isEmpty()) {
                            Log.e("Tag", "blabla")
                            view.showEmptyView()
                        } else {
                            view.showNextPageOfMovies(it)
                            page++
                        }

                    }

                ))
        } else {
            view.showNoMoreMoviesMessage()
        }
    }

    /**
     * This is just for demo purposes, in production we should query
     * the value from PopularTvShowsResponse
     */
    fun morePages(): Boolean = page < LAST_PAGE

}