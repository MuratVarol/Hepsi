package com.varol.hepsi.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenericBanner(
    val image: ImageModel?
) : Parcelable, Model()