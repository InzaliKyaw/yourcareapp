package com.example.yourcare.mvp.view

interface LoginView:BaseView {
    fun navigateToRegisterView()
    fun navigateToHomeScreen(patientId:Long)
}