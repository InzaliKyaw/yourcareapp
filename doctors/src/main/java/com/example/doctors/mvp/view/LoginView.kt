package com.example.doctors.mvp.view

interface LoginView:BaseView{
    fun navigateToHomeScreen(doctorName:String)
    fun navigateToRegisterScreen()
}