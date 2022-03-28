package com.example.authy.network.data.requests


import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phonenumber")
    val phonenumber: String
)