package com.example.yourcare.util

import com.example.shared.data.vos.PatientsVO

fun savePatientToSession(patientsVO: PatientsVO){
    patientsVO.id?.let {
        SessionManager.patient_id = patientsVO.id
        SessionManager.patient_name = patientsVO.name
        SessionManager.patient_email = patientsVO.email
        SessionManager.patient_deviceId = patientsVO.deviceId
        SessionManager.patient_birthdate = patientsVO.birthdate
        SessionManager.patient_bloodpressure = patientsVO.bloodPressure
        SessionManager.patient_weight = patientsVO.weight
        SessionManager.patient_allergymedicine = patientsVO.allergyMedicine
        SessionManager.patient_height = patientsVO.height
        SessionManager.patient_bloodtype = patientsVO.bloodType
        SessionManager.patient_address = patientsVO.address
    }
}