package com.example.yourcare.mvp

interface RegisterPresenter {
    fun onTapRegister(name:String,email:String,password:String,deviceId:String)
}