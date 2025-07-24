package com.jhonprieto.melifinder.di

import com.jhonprieto.data.ProductRepositoryImpl
import com.jhonprieto.domain.SearchProductDetailsUseCase
import com.jhonprieto.domain.SearchProductsUseCase
import com.jhonprieto.domain.repository.ProductRepository
import com.jhonprieto.melifinder.ui.screens.productDetail.ProductDetailViewModel
import com.jhonprieto.melifinder.ui.screens.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    factory { SearchProductsUseCase(get()) }
    factory { SearchProductDetailsUseCase(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { ProductDetailViewModel(get()) }
}
