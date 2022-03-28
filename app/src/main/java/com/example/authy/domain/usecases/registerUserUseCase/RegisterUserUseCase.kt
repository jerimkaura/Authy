package com.example.authy.domain.usecases.registerUserUseCase

import com.example.authy.domain.repository.AuthRepository
import com.example.authy.network.data.requests.RegisterRequest
import com.example.authy.network.data.responses.RegisterResponse
import com.example.authy.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repository: AuthRepository){
    operator fun invoke(request: RegisterRequest): Flow<Resource<RegisterResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.register(request)
            emit(Resource.Success(response))
        }catch (e: HttpException){
            emit(Resource.Error("An error occurred"))
        }catch (e: IOException){
            emit(Resource.Error("Check internet connection"))
        }
    }
}