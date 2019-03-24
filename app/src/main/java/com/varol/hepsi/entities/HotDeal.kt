package com.varol.hepsi.entities

import android.os.Parcelable
import com.varol.hepsi.extension.ToDate
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class HotDeal(
    val title : String?,
    var expirationDate: String?
) : Parcelable, Model() {
    val getExpDate: Date?
    get() {
        return expirationDate?.ToDate()
    }
}