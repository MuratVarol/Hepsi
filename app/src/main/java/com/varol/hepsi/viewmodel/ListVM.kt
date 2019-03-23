package com.varol.hepsi.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.varol.hepsi.base.BaseVM
import com.varol.hepsi.entities.GenericBanner
import com.varol.hepsi.remote.DataHolder
import com.varol.hepsi.usecases.GetListUseCase
import com.varol.hepsi.util.SingleLiveEvent
import com.varol.hepsi.util.listener.ItemClickListener
import plusAssign

class ListVM(
    private val getListUseCase: GetListUseCase
) : BaseVM() {

    val isNeedToResetScrollState = SingleLiveEvent<Boolean>()

    val list = MutableLiveData<MutableList<GenericBanner>>()

    val isLoading = SingleLiveEvent<Boolean>()
    val isRefreshing = SingleLiveEvent<Boolean>()

    val itemClickListener = object : ItemClickListener<GenericBanner> {
        override fun onItemClick(view: View, item: GenericBanner, position: Int) {
            Log.v("test", position.toString())
        }
    }

    init {
        getList(0)
    }

    fun getList(page: Int) {

        isLoading.postValue(true)

        val disposable = getListUseCase.getMoviesByType(1)
            .subscribeOn(getBackgroundScheduler())
            .observeOn(getMainThreadScheduler())
            .subscribe { data ->

                isLoading.postValue(false)
                isRefreshing.postValue(false)

                when (data) {
                    is DataHolder.Success -> {
                        if (page == 0) {
                            list.postValue(data.data.banners)
                            Log.wtf("bannersize", list.value?.size.toString())
                        } else {
                            list += data.data.banners
                        }
                    }
                    is DataHolder.Error -> {
                        errorMessage.postValue("Veriler çekilemedi")
                    }
                }
            }

        disposables.add(disposable)
    }

}