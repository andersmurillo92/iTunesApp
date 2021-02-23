package com.janders.itunesapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Adapter {

    private val baseUrl = "https://itunes.apple.com/"
    private var Service: Service? = null

    fun getService(): Service? {

        // Create an interceptor and indicate log level to use
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        // Associate interceptor to requests
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        if (Service == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()) // <-- Use log level
                .build()
            Service = retrofit?.create<Service>(com.janders.itunesapp.network.Service::class.java)
        }

        return Service
    }
}