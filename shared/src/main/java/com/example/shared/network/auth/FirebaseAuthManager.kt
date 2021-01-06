package com.example.shared.network.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object FirebaseAuthManager: AuthManager {
private val mFirebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()
    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful && it.isComplete){
                onSuccess()
            }else{
                onFailure(it.exception?.message?:"Please check internet connection")
            }
        }
    }

    override fun register(
        email: String,
        password: String,
        userName: String,
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

    override fun patientRegister(
        email: String,
        password: String,
        userName: String,
        deviceId: String,
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

    override fun patientLogin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.signInWithEmailAndPassword(email,password)

    }

    override fun getCurrentUser(onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        var user: FirebaseUser? = mFirebaseAuth.currentUser
        user?.displayName?.let { onSuccess(it) }
    }
}