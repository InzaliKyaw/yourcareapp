package com.example.doctors.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.example.doctors.mvp.view.LoginView

interface LoginPresenter:BasePresenter<LoginView> {
    fun onTapLogin(lifecycleOwner: LifecycleOwner,email:String,password:String)
    fun onTapRegister()
}