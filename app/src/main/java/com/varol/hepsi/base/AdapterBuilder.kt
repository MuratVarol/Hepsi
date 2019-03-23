package com.varol.hepsi.base

import com.varol.hepsi.util.listener.ItemClickListener
import com.varol.hepsi.view.base.BaseRecyclerAdapter

class AdapterBuilder<ModelType>(
    val itemList: List<ModelType>,
    val layoutId: Int,
    val itemClickListener: ItemClickListener<ModelType>?
) {

    fun build(): BaseRecyclerAdapter<ModelType> {
        val baseAdapter = BaseRecyclerAdapter(itemList, layoutId, itemClickListener)
        baseAdapter.updateData(itemList)
        return baseAdapter
    }
}