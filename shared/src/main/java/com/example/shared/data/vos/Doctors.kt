package com.example.shared.data.vos

import com.google.gson.annotations.SerializedName

data class Doctors (
    @SerializedName("id")
    var id:Int = 0,

    @SerializedName("name")
    var name:String = "",

    @SerializedName("speciality")
    var speciality:String = ""
)