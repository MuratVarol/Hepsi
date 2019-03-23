package com.varol.hepsi.extension

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.varol.hepsi.R
import com.varol.hepsi.util.GlideApp

fun ImageView.setImageByUrl(url: String?) {
    if (url?.isNotEmpty() == true) {

        val circularProgressDrawable = CircularProgressDrawable(this.context)
        circularProgressDrawable.apply {
            strokeWidth = 10f
            centerRadius = 50f
            start()
        }

        GlideApp.with(this.context)
            .load(url)
            .placeholder(circularProgressDrawable)
            .into(this)
    } else {
        GlideApp.with(this.context)
            .load(R.drawable.ic_broken_image)
            .into(this)
    }
}