package com.example.authy.domain.repository

import com.example.authy.network.data.AuthyApi
import com.example.authy.network.data.requests.LoginRequest
import com.example.authy.network.data.requests.RegisterRequest
import com.example.authy.network.data.requests.UpdateProfileRequest
import com.example.authy.network.data.responses.LoginResponse
import com.example.authy.network.data.responses.RegisterResponse
import com.example.authy.network.data.responses.UpdateProfileResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthyApi
) {
    suspend fun register(request: RegisterRequest): RegisterResponse = api.register(request)
    suspend fun login(request: LoginRequest): LoginResponse = api.login(request)
    suspend fun updateProfile(request: UpdateProfileRequest): UpdateProfileResponse =
        api.updateProfile(request)
}
