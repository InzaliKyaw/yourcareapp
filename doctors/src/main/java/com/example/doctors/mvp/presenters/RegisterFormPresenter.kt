package com.example.doctors.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.doctors.mvp.view.RegisterFormView

interface RegisterFormPresenter:BasePresenter<RegisterFormView> {
    fun onTapCreateAccount(lifecycleOwner: LifecycleOwner,deviceId:String,userName:String,phone:String,gender:String,speciality:String,experience:Long,degree:String,bio:String,address:String)
}