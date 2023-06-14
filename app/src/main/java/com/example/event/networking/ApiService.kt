package com.example.event.networking

import com.example.event.model.UserData
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getUsers(): Call<List<UserData>>
}