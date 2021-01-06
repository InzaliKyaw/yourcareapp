package com.example.doctors.util

import com.example.shared.data.vos.DoctorsVO
import com.example.shared.data.vos.MedicineVO

fun saveDoctorToSession(doctor : DoctorsVO) {
    doctor.id?.let {
        SessionManager.doctor_id = doctor.id
        SessionManager.doctor_name = doctor.name
        SessionManager.doctor_speciality = doctor.speciality
        SessionManager.device_id = doctor.deviceId
    }
}

fun savePill(medicineVO: MedicineVO){
    medicineVO.id?.let {
        SessionManager.pill_name = medicineVO.pillName
    }
}