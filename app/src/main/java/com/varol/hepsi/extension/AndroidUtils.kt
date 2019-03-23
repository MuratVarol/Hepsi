package com.varol.hepsi.extension

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.varol.hepsi.BuildConfig

fun Context.debugToast(message: CharSequence) {
    if (BuildConfig.DEBUG)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.informToast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.informToast(message: CharSequence) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.debugToast(message: CharSequence) {
    if (BuildConfig.DEBUG)
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
}