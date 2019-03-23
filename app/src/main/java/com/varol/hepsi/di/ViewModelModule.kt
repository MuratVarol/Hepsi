package com.varol.hepsi.di

import com.varol.hepsi.viewmodel.ListVM
import com.varol.hepsi.viewmodel.MainVM
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { MainVM() }
    viewModel { ListVM(get()) }
}