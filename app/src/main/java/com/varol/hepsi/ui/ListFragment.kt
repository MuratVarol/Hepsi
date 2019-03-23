package com.varol.hepsi.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.varol.hepsi.R
import com.varol.hepsi.base.BaseFragment
import com.varol.hepsi.databinding.FragmentListBinding
import com.varol.hepsi.extension.informToast
import com.varol.hepsi.util.listener.EndlessRecyclerViewScrollListener
import com.varol.hepsi.viewmodel.ListVM
import observe

class ListFragment : BaseFragment<ListVM, FragmentListBinding>(ListVM::class) {

    override val getLayoutId: Int = R.layout.fragment_list

    private val visibleThreshold = 3

    lateinit var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        subscribeResetScrollState()
        subscribeErrorMessage()
        subscribeIsRefreshing()

        return binding.root
    }

    private fun subscribeResetScrollState() {
        viewModel.isNeedToResetScrollState.observe(this) {
            if (it == true)
                endlessRecyclerViewScrollListener.resetState()
        }
    }

    private fun subscribeIsRefreshing() {
        viewModel.isRefreshing.observe(this) {
            binding.srl.isRefreshing = it == true
        }
    }

    private fun subscribeErrorMessage() {
        viewModel.errorMessage.observe(this) {
            informToast(it.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewListeners()
    }

    private fun setRecyclerViewListeners() {
        val linearLayoutManager = LinearLayoutManager(context)
        binding.recyclerList.layoutManager = linearLayoutManager
        endlessRecyclerViewScrollListener = object :
            EndlessRecyclerViewScrollListener(linearLayoutManager, visibleThreshold) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.getList(page)
            }
        }

        binding.recyclerList.addOnScrollListener(endlessRecyclerViewScrollListener)

        binding.srl.setOnRefreshListener {
            viewModel.getList(0)
        }

    }


}
