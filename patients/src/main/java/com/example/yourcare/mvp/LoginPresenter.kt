package com.example.yourcare.mvp

import android.content.Context
import androidx.lifecycle.LifecycleOwner

interface LoginPresenter {
    fun onTapLogin(lifecycleOwner: LifecycleOwner,email:String,password:String)
}