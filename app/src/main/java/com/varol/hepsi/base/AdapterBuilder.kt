package com.varol.hepsi.base

import com.varol.hepsi.util.listener.ItemClickListener

class AdapterBuilder<ModelType>(
    val itemList: List<ModelType>,
    val itemClickListener: ItemClickListener<ModelType>?
) {

    fun build(): BaseRecyclerAdapter<ModelType> {
        val baseAdapter = BaseRecyclerAdapter(itemList, itemClickListener)
        baseAdapter.updateData(itemList)
        return baseAdapter
    }
}