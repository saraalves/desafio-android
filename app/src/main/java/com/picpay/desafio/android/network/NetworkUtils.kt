package com.picpay.desafio.android.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {
    companion object {

        private const val BASE_URL = "http://careers.picpay.com/tests/mobdev/"

        private val okHttp: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .build()
        }

        fun getRetrofit(baseUrl: String = BASE_URL): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}