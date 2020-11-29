package com.example.shared.data.model

import com.example.shared.network.auth.AuthManager
import com.example.shared.network.auth.FirebaseAuthManager

object AuthenticationModelImpl:AuthenticationModel {
    override var mAuthManager: AuthManager = FirebaseAuthManager


    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.login(email,password,onSuccess,onFailure)
    }

    override fun register(
        email: String,
        password: String,
        userName: String,
        speciality: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.register(email,password,userName,speciality,onSuccess,onFailure)
    }
}