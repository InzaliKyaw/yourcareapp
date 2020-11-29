package com.example.shared.data.vos

data class Consultation(
    var id:String = "",
    var doctorChat: DoctorChat = DoctorChat(),
    var patientChat: PatientChat = PatientChat(),
    var prescription: Prescription = Prescription()
)