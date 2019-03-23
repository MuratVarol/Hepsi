package com.varol.hepsi.usecases

import android.content.Context
import com.varol.hepsi.entities.ListModel
import com.varol.hepsi.remote.DataHolder
import com.varol.hepsi.remote.repository.ListRepository
import io.reactivex.Single

class GetListUseCase(
    private val context: Context,
    private val listRepository: ListRepository
) {

    fun getMoviesByType(
        page: Int
    ): Single<DataHolder<ListModel>> {
        return listRepository.getDealList(
            page
        )
    }
}