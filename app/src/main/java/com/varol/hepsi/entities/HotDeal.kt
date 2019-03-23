package com.varol.hepsi.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class HotDeal(
    val title : String?,
    val expirationDate : String?
) : Parcelable{
    val getValidThruDate : Date?
    get() {
       return Calendar.getInstance().time
    }
}