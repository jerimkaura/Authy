package com.example.authy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authy.domain.usecases.loginUseCase.LoginUseCase
import com.example.authy.domain.usecases.registerUserUseCase.RegisterUserUseCase
import com.example.authy.domain.usecases.updateProfileUseCase.UpdateProfileUseCase
import com.example.authy.network.data.requests.LoginRequest
import com.example.authy.network.data.requests.RegisterRequest
import com.example.authy.network.data.requests.UpdateProfileRequest
import com.example.authy.util.Resource
import com.example.authy.view.authentication.LoginState
import com.example.authy.view.authentication.RegisterState
import com.example.authy.view.authentication.UpdateProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUseCase: LoginUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase
) : ViewModel() {
    private val registerState: MutableLiveData<RegisterState> = MutableLiveData()
    val _registerState: LiveData<RegisterState>
        get() = registerState

    private val loginState: MutableLiveData<LoginState> = MutableLiveData()
    val _loginState: LiveData<LoginState>
        get() = loginState

    private val updateProfileState: MutableLiveData<UpdateProfileState> = MutableLiveData()
    val _updateProfileState: LiveData<UpdateProfileState>
            get() = updateProfileState

    fun register(registerRequest: RegisterRequest){
        registerUserUseCase(registerRequest).onEach { result->
            when(result){
                is Resource.Success ->{
                    registerState.value = RegisterState(data = result.data)
                }
                is Resource.Loading ->{
                    registerState.value = RegisterState(isLoading = true)
                }
                is Resource.Error -> {
                    registerState.value = result.message?.let { RegisterState(error = it) }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun login(loginRequest: LoginRequest){
        loginUseCase(loginRequest).onEach { result ->
            when(result){
                is Resource.Success ->{
                    loginState.value = LoginState(data = result.data)
                }

                is Resource.Loading -> {
                    loginState.value = LoginState(isLoading = true)
                }

                is Resource.Error -> {
                    loginState.value = result.message?.let { LoginState(error = it) }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateProfile(updateProfileRequest: UpdateProfileRequest){
        updateProfileUseCase(updateProfileRequest).onEach { result ->
            when(result){
                is Resource.Success ->{
                    updateProfileState.value = UpdateProfileState(data = result.data)
                }

                is Resource.Loading -> {
                    updateProfileState.value = UpdateProfileState(isLoading = true)
                }

                is Resource.Error -> {
                    updateProfileState.value = result.message?.let { UpdateProfileState(error = it) }
                }
            }
        }
    }
}