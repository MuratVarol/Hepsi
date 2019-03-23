package com.varol.hepsi.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.varol.hepsi.BR
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import kotlin.reflect.KClass

abstract class BaseActivity<out VM : ViewModel, DB : ViewDataBinding>(viewModelClass: KClass<VM>) :
    AppCompatActivity() {

    //no need for ViewModelProviders
    protected val viewModel: VM by viewModelByClass(viewModelClass)

    abstract val layoutRes: Int

    lateinit var binding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)

    }

    fun loadFragment(containerId: Int, fragment: Fragment, addToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            fragmentTransaction.addToBackStack("")
        }
        fragmentTransaction.replace(containerId, fragment).commit()
    }

}