package com.example.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.shared.data.vos.MedicineVO
import com.example.shared.data.vos.PatientsVO
import com.example.shared.data.vos.Routine
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoutineTypeConverter {
    @TypeConverter
    fun toString(routine: Routine):String{
        return Gson().toJson(routine)
    }

    @TypeConverter
    fun toRoutine(routineListJsonString: String): Routine {
        val routineType = object : TypeToken<Routine>(){}.type
        return Gson().fromJson(routineListJsonString,routineType)
    }
}