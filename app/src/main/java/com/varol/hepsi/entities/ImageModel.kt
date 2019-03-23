package com.varol.hepsi.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageModel(
    val width: Int?,
    val height: Int?,
    val url: String?
) : Parcelable