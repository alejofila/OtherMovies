package com.example.alejofila.themovies.common.presenter

import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter(protected val mainScheduler: Scheduler, protected val backgroundScheduler: Scheduler) {
    protected var disposableBag: CompositeDisposable = CompositeDisposable()

    abstract fun onStart()

    fun onStop() {
        disposableBag.clear()
    }
}