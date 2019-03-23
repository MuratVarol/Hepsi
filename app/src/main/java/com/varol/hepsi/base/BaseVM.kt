package com.varol.hepsi.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

open class BaseVM : ViewModel() {

    val disposables = CompositeDisposable()

    val errorMessage = MutableLiveData<String>()
    val informMessage = MutableLiveData<String>()

    protected fun getBackgroundScheduler(): Scheduler = Schedulers.io()

    protected fun getMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    protected fun startCountdown(period: Long, timeUnit: TimeUnit): Observable<Long> {
        return Observable.interval(period, timeUnit)
            .subscribeOn(getBackgroundScheduler())
            .observeOn(getBackgroundScheduler())
    }

    protected fun startTimer(period: Long, timeUnit: TimeUnit, repeat: Long = 1): Observable<Long> {
        return Observable.timer(period, timeUnit)
            .repeat(repeat)
            .subscribeOn(getBackgroundScheduler())
            .observeOn(getBackgroundScheduler())
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }


}