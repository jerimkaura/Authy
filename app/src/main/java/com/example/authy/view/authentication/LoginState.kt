package com.example.authy.view.authentication

import com.example.authy.network.data.responses.LoginResponse

class LoginState (
    var isLoading:Boolean = false,
    var data: LoginResponse? = null,
    var error: String = ""
)