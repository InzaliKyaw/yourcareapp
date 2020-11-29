package com.example.shared.network.auth

import com.example.shared.data.vos.Doctors

interface FirebaseApi {
    fun addDoctors(id:Int,name:String,speciality:String)
    fun getDoctorCount(onSuccess:(count:Int)->Unit, onFailure:(String)->Unit)
}