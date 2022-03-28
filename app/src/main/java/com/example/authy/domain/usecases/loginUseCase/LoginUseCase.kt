package com.example.authy.domain.usecases.loginUseCase

import com.example.authy.domain.repository.AuthRepository
import com.example.authy.network.data.requests.LoginRequest
import com.example.authy.network.data.responses.LoginResponse
import com.example.authy.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository){
    operator fun invoke(request: LoginRequest): Flow<Resource<LoginResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.login(request)
            emit(Resource.Success(response))
        }catch (e: HttpException){
            emit(Resource.Error("An error occurred"))
        }catch (e: IOException){
            emit(Resource.Error("Check internet connection"))
        }
    }
}