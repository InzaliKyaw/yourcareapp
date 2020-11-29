package com.example.shared.data.model

import com.example.shared.network.auth.AuthManager

interface AuthenticationModel {
    var mAuthManager:AuthManager
    fun login(email:String,password:String,onSuccess:()->Unit,onFailure:(String)->Unit)

    fun register(
        email: String,
        password: String,
        userName:String,
        speciality:String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
}