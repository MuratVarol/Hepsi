package com.varol.hepsi.remote.repository

import com.varol.hepsi.entities.ListModel
import com.varol.hepsi.remote.Api
import com.varol.hepsi.remote.DataHolder
import io.reactivex.Single

class ListRepository(
    private val api : Api
) : BaseRepository() {

    fun getDealList(page: Int): Single<DataHolder<MutableList<ListModel>>>
    {
        return service.sendRequest(api.getDealList(page))
    }
}