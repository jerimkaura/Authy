package com.example.authy.network.data.responses


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access")
    val access: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("phonenumber")
    val phonenumber: String,
    @SerializedName("refresh")
    val refresh: String
)