package com.varol.hepsi.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.ToDate(): Date {
    val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")


    return try {
        val today = df.parse(this)
        return today
    } catch (ex: Exception) {
        Calendar.getInstance().time
    }
}