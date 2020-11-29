package com.example.shared.data.vos

data class ConsultationRequest(
    var id:String = "",
    var patients: Patients = Patients(),
    var specialities: Specialities = Specialities()
)