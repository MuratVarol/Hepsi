package com.varol.hepsi.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.varol.hepsi.BR
import com.varol.hepsi.R
import com.varol.hepsi.entities.GenericBanner
import com.varol.hepsi.entities.HotDeal
import com.varol.hepsi.util.listener.ItemClickListener

class BaseRecyclerAdapter<ModelType>(
    var modelList: List<ModelType>,
    val itemClickListener: ItemClickListener<ModelType>?
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, viewType, parent, false)
        return object : BaseViewHolder(binding) {
            override fun bindData(position: Int) {
                val model = modelList[position]
                itemBinding.setVariable(BR.model, model)
                itemBinding.root.setOnClickListener { view ->
                    itemClickListener?.onItemClick(view, model, position)
                }

            }
        }
    }

    override fun getItemCount(): Int = modelList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindData(position)
    }

    fun updateData(list: List<ModelType>) {
        modelList = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val model = modelList[position]
        return getViewType(model)

    }


    private fun getViewType(model: ModelType): Int {
        return when (model) {
            is HotDeal -> R.layout.item_hot_deals
            is GenericBanner -> R.layout.item_banner
            else -> R.layout.item_hot_deals
        }
    }
}