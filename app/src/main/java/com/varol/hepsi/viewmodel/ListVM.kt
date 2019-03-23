package com.varol.hepsi.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.varol.hepsi.base.BaseVM
import com.varol.hepsi.entities.GenericBanner
import com.varol.hepsi.entities.HotDeal
import com.varol.hepsi.entities.Model
import com.varol.hepsi.remote.DataHolder
import com.varol.hepsi.usecases.GetListUseCase
import com.varol.hepsi.util.SingleLiveEvent
import com.varol.hepsi.util.listener.ItemClickListener
import plusAssign

class ListVM(
    private val getListUseCase: GetListUseCase
) : BaseVM() {

    val isNeedToResetScrollState = SingleLiveEvent<Boolean>()

    val list = MutableLiveData<MutableList<Model>>()

    val isLoading = SingleLiveEvent<Boolean>()
    val isRefreshing = SingleLiveEvent<Boolean>()

    val itemClickListener = object : ItemClickListener<Any> {
        override fun onItemClick(view: View, item: Any, position: Int) {
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

                        mixItems(data.data.banners, data.data.hotDeals)
                    }
                    is DataHolder.Error -> {
                        errorMessage.postValue("Veriler çekilemedi")
                    }
                }
            }

        disposables.add(disposable)
    }

    private fun mixItems(bannerList: MutableList<GenericBanner>, hotDealList: MutableList<HotDeal>) {
        val maxSize = if (bannerList.size >= hotDealList.size) bannerList.size else hotDealList.size

        for (i in 0..maxSize) {
            if (bannerList.size > i)
                list += mutableListOf(bannerList[i] as Model)

            if (bannerList.size > i)
                list += mutableListOf(hotDealList[i] as Model)
        }


    }

}