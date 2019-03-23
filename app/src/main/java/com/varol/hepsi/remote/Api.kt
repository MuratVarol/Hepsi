package com.varol.hepsi.remote

import com.varol.hepsi.entities.ListModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("{page}")
    fun getDealList(
        @Path("page") page: Int
    ): Single<ListModel>
}