package com.varol.hepsi.di

import com.varol.hepsi.usecases.GetListUseCase
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val useCaseModule: Module = module {
    single { GetListUseCase(get(), get()) }
}

