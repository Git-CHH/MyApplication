package com.example.examapplication.di

import com.example.examapplication.service.PhotoApi
import com.example.examapplication.di.DatasourceProperties.JSON_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

val remoteDatasourceModule = module {
    single { getOkHttpClient() }
    single { createService<PhotoApi>(get(), JSON_URL) }
}

object DatasourceProperties {
    const val JSON_URL = "https://jsonplaceholder.typicode.com/"
}

fun getOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.tag("Logging").i(message) })
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(httpLoggingInterceptor)
    builder.connectTimeout(50000, TimeUnit.MILLISECONDS)
    builder.readTimeout(50000, TimeUnit.MILLISECONDS)
    builder.writeTimeout(1000, TimeUnit.MILLISECONDS)
    return builder.build()
}

inline fun <reified T> createService(okHttpClient: OkHttpClient, url: String): T {
    val gson = GsonBuilder()
        .setLenient()
        .create()
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}

inline fun <reified T> createServiceTest(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}