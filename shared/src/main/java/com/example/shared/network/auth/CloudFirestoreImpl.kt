package com.example.shared.network.auth

import android.util.Log
import com.example.shared.data.vos.Doctors
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object CloudFirestoreImpl:FirebaseApi {
    val db = Firebase.firestore
    const val TAG = "Cloud Firestore"
    override fun addDoctors(id: Int, name: String, speciality: String) {
        val doctorMap = hashMapOf(
            "id" to id,
            "name" to name,
            "speciality" to speciality
        )
        db.collection("care")
            .document("doctors")
            .set(doctorMap)
            .addOnSuccessListener {
                Log.d(TAG,"Added Doctor Successfully")
            }
            .addOnFailureListener {
                Log.d(TAG,"Failed Adding Doctor")
            }

    }



    override fun getDoctorCount(onSuccess: (count:Int) -> Unit, onFailure: (String) -> Unit) {
        db.collection("care").document("doctors").addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message?:"Please check connection")
            }?: run {
                val doctorList:MutableList<Doctors> = arrayListOf()
                var result = value
                for(document in Doctors::class.members){
                    val data = value?.data
                    val doctors = Doctors()
                    doctors.id = data?.get("id") as Int
                    doctorList.add(doctors)
                }
                var count = doctorList.size
                onSuccess(count)
            }

        }
    }
}