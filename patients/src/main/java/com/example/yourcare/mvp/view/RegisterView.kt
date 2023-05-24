package com.example.yourcare.mvp.view

interface RegisterView:BaseView {
    fun navigateToLoginScreen(name:String,email:String)
    fun successMessage()

}