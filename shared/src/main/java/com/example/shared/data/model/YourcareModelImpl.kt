package com.example.shared.data.model

import com.example.shared.network.auth.CloudFirestoreImpl
import com.example.shared.network.auth.FirebaseApi

object YourcareModelImpl:YourcareModel {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreImpl


    override fun addDoctors(id: Int, name: String, speciality: String) {
        mFirebaseApi.addDoctors(id,name, speciality)
    }

    override fun getDoctorCount(onSuccess: (count: Int) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getDoctorCount(onSuccess,onFailure)
    }
}