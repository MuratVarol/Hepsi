package com.varol.hepsi.viewmodel

import android.util.Log
import android.view.View
import com.varol.hepsi.base.BaseVM
import com.varol.hepsi.entities.HotDeal
import com.varol.hepsi.util.SingleLiveEvent
import com.varol.hepsi.util.listener.ItemClickListener

class ListVM(

) : BaseVM() {

    val isNeedToResetScrollState = SingleLiveEvent<Boolean>()

    val itemClickListener = object : ItemClickListener<HotDeal> {
        override fun onItemClick(view: View, item: HotDeal, position: Int) {
            Log.v("test", position.toString())
        }
    }

    init {

    }



}