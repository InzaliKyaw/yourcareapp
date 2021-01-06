package com.example.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Patient")
data class PatientsVO(
    //PatientsNode
    @SerializedName("id")
    @PrimaryKey
    var id:Long ?= 0,

    @SerializedName("device_id")
    var deviceId:String? ="",

    @SerializedName("name")
    var name:String ?= "",

    @SerializedName("email")
    var email:String ?= "",

//    @SerializedName("age")
//    var age:Long? = 0,

    @SerializedName("birthdate")
    var birthdate:String ?= "",

    @SerializedName("blood_pressure")
    var bloodPressure:String ?="",

    @SerializedName("weight")
    var weight:String ?="",

    @SerializedName("allergy_medicine")
    var allergyMedicine:String ?="",

    @SerializedName("height")
    var height:String ?= "",

    @SerializedName("blood_type")
    var bloodType:String ?="",

    @SerializedName("address")
    var address:String ?="",

    /*
    @SerializedName("recent_doctors")
    var recentDoctorsVO: DoctorsVO ?= DoctorsVO(),
     */

//    @SerializedName("case_summary")
//    var caseSummaryVO:List<CaseSummaryVO> ?= arrayListOf(),


    @SerializedName("specialities")
    var specialitiesVO: SpecialitiesVO ?= SpecialitiesVO()


)

fun MutableMap<String,Any>.convertToPatientVO(): PatientsVO {
    val patientsVO = PatientsVO()
    patientsVO.id = this.get("id") as? Long
    patientsVO.name = this.get("name") as? String
    patientsVO.email = this.get("email") as? String
    patientsVO.deviceId = this.get("deviceId") as? String
    patientsVO.birthdate = this.get("birthdate") as? String
    patientsVO.bloodPressure = this.get("bloodPressure")as? String
    patientsVO.weight = this.get("weight") as? String
    patientsVO.allergyMedicine = this.get("allergyMedicine") as? String
    patientsVO.height = this.get("height") as? String
    patientsVO.bloodType = this.get("bloodType") as? String
    patientsVO.address = this.get("address") as? String
    return patientsVO
}