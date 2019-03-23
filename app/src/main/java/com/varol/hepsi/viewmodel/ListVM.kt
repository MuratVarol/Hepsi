package com.varol.hepsi.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.varol.hepsi.base.BaseVM
import com.varol.hepsi.entities.GenericBanner
import com.varol.hepsi.entities.ListModel
import com.varol.hepsi.remote.DataHolder
import com.varol.hepsi.usecases.GetListUseCase
import com.varol.hepsi.util.SingleLiveEvent
import com.varol.hepsi.util.listener.ItemClickListener
import plusAssign

class ListVM(
    val getListUseCase: GetListUseCase
) : BaseVM() {

    val isNeedToResetScrollState = SingleLiveEvent<Boolean>()

    val list = MutableLiveData<MutableList<ListModel>>()

    val genericBannerList = MutableLiveData<MutableList<GenericBanner>>()

    val itemClickListener = object : ItemClickListener<GenericBanner> {
        override fun onItemClick(view: View, item: GenericBanner, position: Int) {
            Log.v("test", position.toString())
        }
    }

    init {
        getList(1)
    }

    fun getList(page: Int) {
        val disposable = getListUseCase.getMoviesByType(1)
            .subscribeOn(getBackgroundScheduler())
            .observeOn(getMainThreadScheduler())
            .subscribe { data ->
                when (data) {
                    is DataHolder.Success -> {
                        if (page == 1) {
                            list.postValue(data.data)
                        } else {
                            list += data.data
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