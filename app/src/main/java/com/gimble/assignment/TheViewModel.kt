package com.gimble.assignment

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gimble.assignment.data.remote.dashboardapi.DashboardData
import com.gimble.assignment.objects.DashboardObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TheViewModel : ViewModel() {
    private val dashData = MutableLiveData<DashboardData>()
    val apiData: LiveData<DashboardData> get() = dashData



    public fun getDataforDashboard (){
        val accessToken ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"
        val call = DashboardObject.apiService.getDashboardData("Bearer $accessToken")
        call.enqueue(object : Callback<DashboardData> {
            override fun onResponse(call: Call<DashboardData>, response: Response<DashboardData>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if(data!=null){
                    dashData.value = data}


                    Log.d("success message", " response from api was successful ")
                } else {
                    Log.d("error message", " response from api not successful")
                }
            }

            override fun onFailure(call: Call<DashboardData>, t: Throwable) {
                Log.d("error message", " failure for the get request for dashboard")
            }
        })
    }
}