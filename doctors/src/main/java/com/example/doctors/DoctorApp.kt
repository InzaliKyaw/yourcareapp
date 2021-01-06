package com.example.doctors

import android.app.Application
import com.example.doctors.util.SessionManager
import com.example.doctors.util.SessionManager.init
import com.example.shared.data.model.YourcareModelImpl
import com.google.firebase.FirebaseApp

class DoctorApp:Application() {

    override fun onCreate() {
        super.onCreate()
        //FirebaseApp.initializeApp(this)
        FirebaseApp.initializeApp(this)
        //initDB
        YourcareModelImpl.initDatabase(this)

        SessionManager.init(applicationContext)

    }
}