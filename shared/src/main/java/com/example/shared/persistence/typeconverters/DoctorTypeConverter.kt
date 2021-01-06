package com.example.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.shared.data.vos.DoctorsVO
import com.example.shared.data.vos.PatientsVO
import com.example.shared.data.vos.SpecialitiesVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DoctorTypeConverter {
    @TypeConverter
    fun toString(doctor: DoctorsVO):String{
        return Gson().toJson(doctor)
    }

    @TypeConverter
    fun toDoctors(doctorListJsonString: String): DoctorsVO {
        val specialitiesType = object : TypeToken<DoctorsVO>(){}.type
        return Gson().fromJson(doctorListJsonString,specialitiesType)
    }
}