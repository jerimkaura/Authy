package com.example.authy.view.authentication

import com.example.authy.network.data.responses.RegisterResponse

class RegisterState (
    var isLoading:Boolean = false,
    var data: RegisterResponse? = null,
    var error: String = ""
)