package com.jhonprieto.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkModule {
    private const val BASE_URL = BuildConfig.MELI_BASE_URL
    private const val TOKEN = BuildConfig.MELI_BEARER_TOKEN

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun provideHttpClient(log: Boolean = false): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", TOKEN)
                    .build()
                chain.proceed(request)
            }
        if (log) {
            builder.addInterceptor(
                okhttp3.logging.HttpLoggingInterceptor().apply {
                    level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
                }
            )
        }
        return builder.build()
    }

    fun provideRetrofit(log: Boolean = false): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideHttpClient(log))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    inline fun <reified T> createService(log: Boolean = false): T =
        provideRetrofit(log).create(T::class.java)
}
