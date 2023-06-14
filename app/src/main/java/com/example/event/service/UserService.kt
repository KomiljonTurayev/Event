package com.example.event.service

import com.example.event.model.UserData
import com.example.event.networking.ApiClient
import com.example.event.networking.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

object UserService {

    @GET("users")
    fun getUsersList(): List<UserData> {
        val list = ArrayList<UserData>()
        ApiClient.getRetrofit().create(ApiService::class.java)
            .getUsers()
            .enqueue(object : Callback<List<UserData>> {
                override fun onResponse(
                    call: Call<List<UserData>>,
                    response: Response<List<UserData>>
                ) {
                    if (response.isSuccessful) {
                        list.addAll(response.body() ?: emptyList())
                    }
                }

                override fun onFailure(call: Call<List<UserData>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return list
    }

}