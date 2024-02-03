package com.gimble.assignment.interfaces

import com.gimble.assignment.data.remote.dashboardapi.DashboardData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface DashboardApi {
    @GET("api/v1/dashboardNew")
    fun getDashboardData(@Header("Authorization") token: String): Call<DashboardData>
}