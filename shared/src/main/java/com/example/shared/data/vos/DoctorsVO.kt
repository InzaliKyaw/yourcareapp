package com.example.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Doctors")
data class DoctorsVO (
    @SerializedName("id")
    @PrimaryKey
    var id:Long ?= 0,

    @SerializedName("name")
    var name:String ?= "",

    @SerializedName("email")
    var email:String ?= null,

    @SerializedName("speciality")
    var speciality:String ?= "",

    @SerializedName("device_id")
    var deviceId:String ?= null,

    @SerializedName("doctor_casesummary")
    var doctorCasesummary:String ?= null,

    //RegisterForem
    @SerializedName("gender")
   var gender:String ?= null,

    @SerializedName("experience")
    var experience:Long ?= null,

    @SerializedName("degree")
    var degree:String ?= null,

    @SerializedName("bio")
    var bio:String ?= null,

    @SerializedName("address")
    var address:String ?= null,

    @SerializedName("phone")
    var phone:String ?= null,

)