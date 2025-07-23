package com.jhonprieto.network

import com.jhonprieto.data.remote.ApiService
import org.koin.dsl.module

val networkModule = module {
    single<ApiService> { NetworkModule.createService<ApiService>() }
}
