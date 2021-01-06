package com.example.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.shared.data.vos.PatientsVO
import com.example.shared.data.vos.SpecialitiesVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpecialitiesTypeConverter {
    @TypeConverter
    fun toString(specialities: SpecialitiesVO):String{
        return Gson().toJson(specialities)
    }

    @TypeConverter
    fun toSpecialities(specialitiesListJsonString: String):SpecialitiesVO{
        val specialitiesType = object : TypeToken<SpecialitiesVO>(){}.type
        return Gson().fromJson(specialitiesListJsonString,specialitiesType)
    }
}