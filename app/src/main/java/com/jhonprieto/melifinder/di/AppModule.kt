package com.jhonprieto.melifinder.di

import com.jhonprieto.data.ProductRepositoryImpl
import com.jhonprieto.domain.SearchProductsUseCase
import com.jhonprieto.domain.repository.ProductRepository
import com.jhonprieto.melifinder.ui.screens.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    factory { SearchProductsUseCase(get()) }
    viewModel { SearchViewModel(get()) }
}
