package com.example.shared.data.vos

import android.location.Address

data class CheckOut(
        var id:String = "",
        var patients: Patients = Patients(),
        var prescription: Prescription = Prescription(),
        var address:String = "",
        var dailyRoutine:String = ""


)