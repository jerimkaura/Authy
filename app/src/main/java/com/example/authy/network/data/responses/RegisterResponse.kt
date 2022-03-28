package com.example.authy.network.data.responses


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("User")
    val user: User
)