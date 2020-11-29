package com.example.doctors.mvp.view

import android.content.Context

interface BaseView {
    fun getAppContext():Context
    fun showError(error : String)
}