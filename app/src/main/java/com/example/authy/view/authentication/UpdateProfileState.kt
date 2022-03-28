package com.example.authy.view.authentication

import com.example.authy.network.data.responses.UpdateProfileResponse

class UpdateProfileState(
    var isLoading: Boolean = false,
    var data: UpdateProfileResponse? = null,
    var error: String = ""
)