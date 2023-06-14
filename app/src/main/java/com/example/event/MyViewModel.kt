package com.example.event

import android.hardware.usb.UsbRequest
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.event.model.UserData
import com.example.event.networking.ApiClient
import com.example.event.networking.ApiService
import com.example.event.utils.UserResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {

    private val liveData = MutableLiveData<UserResource>(UserResource.Loading)

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        ApiClient.getRetrofit().create(ApiService::class.java)
            .getUsers()
            .enqueue(object : Callback<List<UserData>> {
                override fun onResponse(
                    call: Call<List<UserData>>,
                    response: Response<List<UserData>>
                ) {
                    if (response.isSuccessful) {
                        liveData.postValue(UserResource.Success(response.body()))
                    } else {
                        when (response.code()) {
                            in 400..499 -> liveData.postValue(UserResource.Error("Client Error"))
                            in 500..599 -> liveData.postValue(UserResource.Error("Server Error"))
                        }
                    }
                }

                override fun onFailure(call: Call<List<UserData>>, t: Throwable) {
                    Log.d("onFailure", "onFailure: $t")
                    liveData.postValue(UserResource.Error(t.message ?: ""))
                }

            })
    }

    fun getUserList(): LiveData<UserResource> {
        return liveData
    }


}