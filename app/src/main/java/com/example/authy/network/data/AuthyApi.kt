package com.example.authy.network.data

import com.example.authy.network.data.requests.LoginRequest
import com.example.authy.network.data.requests.RegisterRequest
import com.example.authy.network.data.requests.UpdateProfileRequest
import com.example.authy.network.data.responses.LoginResponse
import com.example.authy.network.data.responses.RegisterResponse
import com.example.authy.network.data.responses.UpdateProfileResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthyApi {
    companion object{
        const val REGISTER = "register/"
        const val LOGIN = "login/"
        const val UPDATE_PROFILE = "update/"
    }

    @POST(REGISTER)
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse

    @POST(LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST(UPDATE_PROFILE)
    suspend fun updateProfile(@Body updateProfileRequest: UpdateProfileRequest): UpdateProfileResponse
}