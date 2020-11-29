package com.example.shared.network.auth

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthManager:AuthManager {
private val mFirebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()
    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.signInWithEmailAndPassword(email,password)
    }

    override fun register(
        email: String,
        password: String,
        userName: String,
        speciality: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful && it.isComplete){
                onSuccess()
            }else{
                onFailure(it.exception?.message?:"Please check internet connection")
            }
        }
    }
}