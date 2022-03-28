package com.example.authy.network.data.responses


import com.google.gson.annotations.SerializedName

data class UpdateProfileResponse(
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("phonenumber")
    val phonenumber: String
)