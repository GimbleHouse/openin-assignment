package com.gimble.assignment.objects

import com.gimble.assignment.interfaces.DashboardApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DashboardObject {
    private const val BASE_URL = "https://api.inopenapp.com/"
    private val okHttpClient = OkHttpClient.Builder().build()

    val apiService: DashboardApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DashboardApi::class.java)
}