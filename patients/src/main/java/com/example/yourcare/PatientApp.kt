package com.example.yourcare

import android.app.Application
import com.example.shared.data.model.YourcareModelImpl
import com.example.yourcare.util.SessionManager
import com.google.firebase.FirebaseApp

class PatientApp:Application() {
    override fun onCreate() {
        super.onCreate()
        YourcareModelImpl.initDatabase(this)
        FirebaseApp.initializeApp(this)
        SessionManager.init(applicationContext)

    }
}