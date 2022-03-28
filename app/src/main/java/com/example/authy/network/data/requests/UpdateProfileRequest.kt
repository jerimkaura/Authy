package com.example.authy.network.data.requests


import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("phonenumber")
    val phonenumber: String
)