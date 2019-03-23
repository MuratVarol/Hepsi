package com.varol.hepsi.view.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.varol.hepsi.BR
import com.varol.hepsi.util.listener.ItemClickListener

class BaseRecyclerAdapter<ModelType>(
    var modelList: List<ModelType>,
    val itemLayoutId: Int,
    val itemClickListener: ItemClickListener<ModelType>?
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, itemLayoutId, parent, false)
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

}