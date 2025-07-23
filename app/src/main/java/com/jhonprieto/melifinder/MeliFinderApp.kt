package com.jhonprieto.melifinder

import android.app.Application
import com.jhonprieto.melifinder.di.appModule
import com.jhonprieto.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MeliFinderApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MeliFinderApp)
            modules(listOf(networkModule, appModule))
        }
    }
}
