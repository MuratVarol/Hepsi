package com.varol.hepsi.di

import com.varol.hepsi.remote.repository.ListRepository
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val repositoryModule: Module = module {
    single { ListRepository(get()) }
}
