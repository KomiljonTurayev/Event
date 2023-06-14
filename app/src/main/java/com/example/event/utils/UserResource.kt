package com.example.event.utils

import com.example.event.model.UserData


sealed class UserResource {

    object Loading : UserResource()

    data class Success(val list: List<UserData>?) : UserResource()

    data class Error(val message: String) : UserResource()
}
