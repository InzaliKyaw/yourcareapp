package com.example.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.shared.data.vos.MedicineVO
import com.example.shared.data.vos.PatientsVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PatientTypeConverter {
    @TypeConverter
    fun toString(patients: PatientsVO):String{
        return Gson().toJson(patients)
    }

    @TypeConverter
    fun toPatient(patientsListJsonString: String):PatientsVO{
        val patientsType = object : TypeToken<PatientsVO>(){}.type
        return Gson().fromJson(patientsListJsonString,patientsType)
    }
}