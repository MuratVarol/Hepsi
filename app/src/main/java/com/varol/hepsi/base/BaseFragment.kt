package com.varol.hepsi.base

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.varol.hepsi.BR
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import kotlin.reflect.KClass


abstract class BaseFragment<out VM : ViewModel, DB : ViewDataBinding>(viewModelClass: KClass<VM>) : Fragment() {

    //no need for ViewModelProviders
    val viewModel: VM by viewModelByClass(viewModelClass)

    protected lateinit var binding: DB

    abstract val getLayoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    fun loadFragment(
        containerId: Int,
        fragment: Fragment,
        fm: FragmentManager?,
        addToBackStack: Boolean
    ) {
        val ft = fm?.beginTransaction()
        if (addToBackStack) {
            ft?.addToBackStack("")
        }
        ft?.replace(containerId, fragment)?.commit()
    }

    fun clearBackStack(manager: FragmentManager?) {
        manager?.apply {
            if (backStackEntryCount > 1) {
                val first = getBackStackEntryAt(1)
                popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }

    }


}