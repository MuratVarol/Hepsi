package com.varol.hepsi.entities

import com.google.gson.annotations.SerializedName

data class ListModel(
    @SerializedName("hotDeals")
    val hotDeals: MutableList<HotDeal>,

    @SerializedName("banners")
    val banners: MutableList<GenericBanner>

)