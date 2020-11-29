package com.example.shared.data.model

import com.example.shared.network.auth.FirebaseApi

interface YourcareModel {
    var mFirebaseApi:FirebaseApi

    fun addDoctors(id:Int,name:String,speciality:String)
    fun getDoctorCount(onSuccess:(count: Int)->Unit, onFailure:(String)->Unit)
}