package com.varol.hepsi

import android.app.Application
import com.varol.hepsi.di.networkModule
import com.varol.hepsi.di.repositoryModule
import com.varol.hepsi.di.useCaseModule
import com.varol.hepsi.di.viewModelModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin(
            this,
            listOf(
                networkModule,
                viewModelModule,
                useCaseModule,
                repositoryModule
            )
        )
    }
}