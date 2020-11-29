package com.example.doctors.mvp.presenters

import android.content.Context
import com.example.doctors.mvp.view.LoginView

interface LoginPresenter:BasePresenter<LoginView> {
    fun onTapLogin(context: Context,email:String,password:String)
    fun onTapRegister()
}