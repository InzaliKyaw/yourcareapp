package com.example.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shared.persistence.typeconverters.MedicineTypeConverter
import com.example.shared.persistence.typeconverters.RelatedQuestionTypeConverter
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
@IgnoreExtraProperties
@Entity(tableName = "Speciality")
data class SpecialitiesVO(
    @SerializedName("id")
    @PrimaryKey
    var id:String = "",

    @SerializedName("name")
    var name:String ?= "",

    @SerializedName("image")
    var image:String ?= "",

    @SerializedName("related_question")
    var relatedQuestionVO:List<RelatedQuestionVO> ?= arrayListOf(),

    @SerializedName("most_used_medicine")
    var medicineVO: List<MedicineVO> ?= arrayListOf()
)