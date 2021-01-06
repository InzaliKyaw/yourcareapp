package com.example.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.shared.data.vos.MedicineVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MedicineTypeConverter {

    @TypeConverter
    fun toString(medicine: List<MedicineVO>):String{
        return Gson().toJson(medicine)
    }

    @TypeConverter
    fun toMedicine(medicineListJsonString: String):List<MedicineVO>{
        val medicineType = object : TypeToken<List<MedicineVO>>(){}.type
        return Gson().fromJson(medicineListJsonString,medicineType)
    }
}