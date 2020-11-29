package com.example.doctors

import android.app.Application
import com.google.firebase.FirebaseApp

class DoctorApp:Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}