package com.santosh.navi.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    private const val BASE_URL = "https://api.github.com"

    val retrofitInstance: RetrofitInterface by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient()
            ).build().create(RetrofitInterface::class.java)
    }
}