package com.varol.hepsi.ui

import android.os.Bundle
import com.varol.hepsi.R
import com.varol.hepsi.base.BaseActivity
import com.varol.hepsi.databinding.ActivityMainBinding
import com.varol.hepsi.viewmodel.MainVM

class MainActivity : BaseActivity<MainVM, ActivityMainBinding>(MainVM::class) {
    override val layoutRes: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadFragment(R.id.main_container, ListFragment(),true)

    }

    override fun onBackPressed() {
        /**
         * DO NOTHING
         */
    }
}