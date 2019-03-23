package com.varol.hepsi.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceItemDecoration constructor(private val verticalSpaceHeight: Int = 0) :
    RecyclerView.ItemDecoration() {

    override
    fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) != parent.adapter?.itemCount ?: 1 - 1) {
            outRect.bottom = verticalSpaceHeight
        }
    }

}