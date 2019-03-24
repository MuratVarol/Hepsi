package com.varol.hepsi.base

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.varol.hepsi.BR
import com.varol.hepsi.R
import com.varol.hepsi.entities.GenericBanner
import com.varol.hepsi.entities.HotDeal
import com.varol.hepsi.extension.getRemaininTimeAsString
import com.varol.hepsi.util.listener.ItemClickListener
import kotlinx.android.synthetic.main.item_hot_deals.view.*


class BaseRecyclerAdapter<ModelType>(
    var modelList: List<ModelType>,
    val itemClickListener: ItemClickListener<ModelType>?
) : RecyclerView.Adapter<BaseRecyclerAdapter<ModelType>.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, viewType, parent, false)
        return BaseViewHolder(binding)

    }

    inner class BaseViewHolder(val itemBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var countDownTimer: CountDownTimer? = null

    }

    override fun getItemCount(): Int = modelList.size


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        /**
         * BELOW PART BROKE GENERIC CLASS, BUT IT WAS FASTEST SOLUTION
         * todo: make below part more generic and clean unnecessary codes
         */

        val model = modelList[position]
        if (model is HotDeal) {

            val debug = model.getExpDate
            val pos = position

            holder.countDownTimer?.cancel()
            holder.countDownTimer = null

            holder.countDownTimer = object : CountDownTimer(model.getExpDate?.time ?: 10_000L, 1000L) {
                override fun onTick(millisUntilFinished: Long) {
                    if (holder.itemBinding.root.tv_deal_remaining.text != model.getExpDate?.getRemaininTimeAsString())
                        holder.itemBinding.root.tv_deal_remaining.text = model.getExpDate?.getRemaininTimeAsString()
                }

                override fun onFinish() {
                    if (holder.itemBinding.root.tv_deal_remaining.text != model.getExpDate?.getRemaininTimeAsString())
                        holder.itemBinding.root.tv_deal_remaining.text = model.getExpDate?.getRemaininTimeAsString()
                }
            }.start()

        }
        holder.itemBinding.setVariable(BR.model, model)
        holder.itemBinding.root.setOnClickListener { view ->
            itemClickListener?.onItemClick(view, model, position)
        }


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