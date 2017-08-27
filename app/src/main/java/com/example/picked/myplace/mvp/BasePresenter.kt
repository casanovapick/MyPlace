package com.example.picked.myplace.mvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable


abstract class BasePresenter<T : MvpView>(protected var view: T?) : LifecycleObserver {

    protected val compositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stop() {
        compositeDisposable.dispose()
        view = null
    }
}
