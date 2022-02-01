package com.tanvir.currency.service


import com.tanvir.currency.BuildConfig

import retrofit2.Retrofit

import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import java.util.concurrent.TimeUnit


object RetrofitInstance {
    private var retrofit: Retrofit? = null

    private const val BASE_URL = BuildConfig.BASE_URL

    val service: CurrencyDataService
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(provideHttpClient())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            }
            return retrofit!!.create(CurrencyDataService::class.java)
        }// .addInterceptor(interceptor)


    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

}