package com.example.mykotlin.sunnyweather.logic.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author yang on 2020/6/6
 */
object ServiceCreator {
    private const val BASE_URL = "https://api.caiyunapp.com/"


    private val logging =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


    private val okHttp = OkHttpClient.Builder()
        .retryOnConnectionFailure(false)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)


}