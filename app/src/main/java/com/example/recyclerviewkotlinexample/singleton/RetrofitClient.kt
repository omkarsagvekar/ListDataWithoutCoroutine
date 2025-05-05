package com.example.recyclerviewkotlinexample.singleton

import TimingInterceptor
import com.example.recyclerviewkotlinexample.apiInterface.ApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    val BASE_URL  = "https://jsonplaceholder.typicode.com/"

    val okHttpClient = OkHttpClient.Builder().
        addInterceptor(TimingInterceptor()).
            connectTimeout(30, TimeUnit.SECONDS).
            readTimeout(30, TimeUnit.SECONDS).
            build()

    val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).
                client(okHttpClient).
                addConverterFactory(GsonConverterFactory.create()).
                build()
    }

    val api: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}