package com.example.alejofila.themovies.di


import com.alejofila.domain.MoviesRepository
import com.alejofila.domain.usecase.GetMoviesByKeywordUseCase
import com.alejofila.domain.usecase.GetMoviesByKeywordUseCaseImpl
import com.alejofila.domain.usecase.GetPopularMoviesUseCase
import com.alejofila.domain.usecase.GetPopularMoviesUseCaseImpl
import com.example.alejofila.data.network.TmdbApi
import com.example.alejofila.data.network.TmdbService
import com.example.alejofila.data.repository.MoviesRepositoryImpl
import com.example.alejofila.themovies.di.Constants.BACKGROUND_SCHEDULER
import com.example.alejofila.themovies.di.Constants.MAIN_SCHEDULER
import com.example.alejofila.themovies.movies.presenter.PopularMoviesPresenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.module

object Constants{
    const val MAIN_SCHEDULER = "mainThreadScheduler"
    const val BACKGROUND_SCHEDULER ="backgroundThreadScheduler"
}
val appModule = module{



    single<TmdbApi>{ TmdbService.getTmdbService()}
    single<MoviesRepository>{ MoviesRepositoryImpl(get()) }
    single<GetPopularMoviesUseCase>{ GetPopularMoviesUseCaseImpl(get()) }
    single<GetMoviesByKeywordUseCase>{ GetMoviesByKeywordUseCaseImpl(get()) }

    single<Scheduler>(MAIN_SCHEDULER){ AndroidSchedulers.mainThread()}
    single<Scheduler>(BACKGROUND_SCHEDULER){ Schedulers.io()}
    factory<PopularMoviesPresenter>{ PopularMoviesPresenter(get(),get(),get(MAIN_SCHEDULER),get(BACKGROUND_SCHEDULER)) }




}
