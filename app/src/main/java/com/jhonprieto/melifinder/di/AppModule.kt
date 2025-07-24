package com.jhonprieto.melifinder.di

import com.jhonprieto.data.repositryImpl.CategoryRepositoryImpl
import com.jhonprieto.data.repositryImpl.ProductRepositoryImpl
import com.jhonprieto.domain.repository.CategoryRepository
import com.jhonprieto.domain.repository.ProductRepository
import com.jhonprieto.domain.usecases.GetCategoriesUseCase
import com.jhonprieto.domain.usecases.GetCategoryDetailUseCase
import com.jhonprieto.domain.usecases.SearchProductDetailsUseCase
import com.jhonprieto.domain.usecases.SearchProductsUseCase
import com.jhonprieto.melifinder.ui.screens.category.CategoryViewModel
import com.jhonprieto.melifinder.ui.screens.productDetail.ProductDetailViewModel
import com.jhonprieto.melifinder.ui.screens.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }

    factory { SearchProductsUseCase(get()) }
    factory { SearchProductDetailsUseCase(get()) }
    single { GetCategoriesUseCase(get()) }
    single { GetCategoryDetailUseCase(get()) }

    viewModel { SearchViewModel(get()) }
    viewModel { ProductDetailViewModel(get()) }
    viewModel { CategoryViewModel(get(), get()) }
}
