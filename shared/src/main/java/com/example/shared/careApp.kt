package com.example.shared

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class careApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
    }
}