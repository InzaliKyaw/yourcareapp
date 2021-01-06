package com.example.shared.network.auth

interface AuthManager {
    fun login(email:String,password:String,onSuccess:()->Unit,onFailure:(String)->Unit)
    fun register(email: String,password: String,userName:String,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun patientRegister(email: String,password: String,userName: String,deviceId:String,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun patientLogin(email:String,password:String,onSuccess:()->Unit,onFailure:(String)->Unit)
    fun getCurrentUser(onSuccess: (String) -> Unit,onFailure: (String) -> Unit)
}