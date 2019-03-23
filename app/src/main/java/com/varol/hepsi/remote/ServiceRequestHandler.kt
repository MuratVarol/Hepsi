package com.varol.hepsi.remote

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

object ServiceRequestHandler {

    fun <T> sendRequest(call: Single<T>): Single<DataHolder<T>> {
        return call
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .map<DataHolder<T>> {
                DataHolder.Success(it)
            }
            .onErrorResumeNext { throwable: Throwable ->
                Single.just(
                    DataHolder.Error<T>(throwable)
                ) as Single<out DataHolder<T>>
            }
            .doOnError { t: Throwable -> t.printStackTrace() }
    }

    fun <T> sendRequest(call: Flowable<T>): Flowable<DataHolder<T>> {
        return call
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .map<DataHolder<T>> {
                DataHolder.Success(it)
            }
            .onErrorResumeNext { throwable: Throwable ->
                Flowable.just(
                    DataHolder.Error<T>(throwable)
                ) as Flowable<out DataHolder<T>>
            }
            .doOnError { t: Throwable -> t.printStackTrace() }
    }

}
