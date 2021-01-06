package com.example.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "GeneralQuestionTemplate")
data class GeneralQuestionTemplateVO (

    @SerializedName("id")
    @PrimaryKey
    var id:Long = 0,

    @SerializedName("question")
    var question:String = "",

    @SerializedName("type")
    var type:String = ""
)