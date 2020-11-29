package com.example.doctors.mvp.presenters

import android.content.Context
import com.example.doctors.mvp.view.RegisterView

interface RegisterPresenter:BasePresenter<RegisterView>{
    fun onTapRegister(context: Context,email:String,password:String,userName:String,speciality:String)
}