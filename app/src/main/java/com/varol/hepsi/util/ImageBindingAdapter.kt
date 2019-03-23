package com.varol.hepsi.util

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.varol.hepsi.extension.setImageByUrl

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    imageView.setImageByUrl(url)
}


@BindingAdapter("setBitmap")
fun setImageBitmap(imageView: ImageView, bitmap: Bitmap?) {

    bitmap?.let {
        imageView.setImageBitmap(bitmap)
    }
}
