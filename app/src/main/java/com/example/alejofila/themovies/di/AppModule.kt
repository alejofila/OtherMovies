package com.example.alejofila.themovies.di


import com.alejofila.domain.TvShowsRepository
import com.alejofila.domain.usecase.GetPopularTvShowsUseCase
import com.alejofila.domain.usecase.GetPopularTvShowsUseCaseImpl

import com.example.alejofila.data.network.TvShowService
import com.example.alejofila.data.network.TmdbApi
import com.example.alejofila.data.repository.TvShowsRepositoryImpl
import com.example.alejofila.themovies.di.Constants.BACKGROUND_SCHEDULER
import com.example.alejofila.themovies.di.Constants.MAIN_SCHEDULER
import com.example.alejofila.themovies.populartv.presenter.PopularTvShowsPresenter

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.module

object Constants{
    const val MAIN_SCHEDULER = "mainThreadScheduler"
    const val BACKGROUND_SCHEDULER ="backgroundThreadScheduler"
}
val appModule = module{



    single<TmdbApi>{ TvShowService.getTVShowsApi()}
    single<TvShowsRepository>{ TvShowsRepositoryImpl(get()) }
    single<GetPopularTvShowsUseCase>{ GetPopularTvShowsUseCaseImpl(get()) }
    single<Scheduler>(MAIN_SCHEDULER){ AndroidSchedulers.mainThread()}
    single<Scheduler>(BACKGROUND_SCHEDULER){ Schedulers.io()}
    factory<PopularTvShowsPresenter>{ PopularTvShowsPresenter(get(),get(MAIN_SCHEDULER),get(BACKGROUND_SCHEDULER)) }




}
