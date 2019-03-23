package com.varol.hepsi.entities

import com.google.gson.annotations.SerializedName

data class ListModel(
    @SerializedName("hotDeals")
    val hotDeals: List<HotDeal>,

    @SerializedName("banners")
    val banners: List<GenericBanner>

)