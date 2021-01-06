package com.example.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ConsultationRequest")
data class ConsultationRequestVO(
    @SerializedName("id")
    @PrimaryKey
    var id: Long = 0,

    @SerializedName("patients")
    var patientsVO: PatientsVO? = PatientsVO(),

    @SerializedName("response_status")
    var responseStatus: String? = "",

    @SerializedName("specialities")
    var specialitiesVO: SpecialitiesVO? = SpecialitiesVO(),

    @SerializedName("time")
    var time: String? = "",

    @SerializedName("date")
    var date: String? = ""

)

fun MutableMap<String, Any>.convertToConsultationRequestVO(): ConsultationRequestVO {
    var consultationRequestVO = ConsultationRequestVO()
    consultationRequestVO.id = this?.get("id") as Long
    consultationRequestVO.patientsVO =
        toConvertPatient(this?.get("patients") as HashMap<String, String>?)
    consultationRequestVO.date = this?.get("date") as String
    consultationRequestVO.time = this?.get("time") as String
    if (this?.get("responseStatus") == null){
        consultationRequestVO.responseStatus = ""
    }
    consultationRequestVO.responseStatus = this?.get("responseStatus") as String
    //consultationRequestVO.specialitiesVO = toConvertSpeciality(this?.get("specialities")as HashMap<String,String>)
    return consultationRequestVO
}

fun toConvertPatient(data: HashMap<String, String>?): PatientsVO? {
    data?.let {
        val patientsVO = PatientsVO()
        if (data.get("id") == null) {
            patientsVO.id = 0
        }
        patientsVO.id = data.get("id") as Long
        if (data.get("name") == null) {
            patientsVO.name = ""
        }
        patientsVO.name = data?.get("name") as String
        if (data.get("email") == null) {
            patientsVO.email = ""
        }
        patientsVO.email = data?.get("email") as String

        if (data.get("deviceId") == null) {
            patientsVO.deviceId = ""
        }
        patientsVO.deviceId = data?.get("deviceId") as String

        if (data.get("birthdate") == null) {
            patientsVO.birthdate = ""
        }
        patientsVO.birthdate = data?.get("birthdate") as String

        if (data.get("bloodPressure") == null) {
            patientsVO.bloodPressure = ""
        }
        patientsVO.bloodPressure = data?.get("bloodPressure") as String
        if (data.get("weight") == null) {
            patientsVO.weight = ""
        }
        patientsVO.weight = data?.get("weight") as String
        if (data.get("allergyMedicine") == null){
            patientsVO.allergyMedicine = ""
        }
        patientsVO.allergyMedicine = data?.get("allergyMedicine") as String

        if (data.get("bloodType") == null){
            patientsVO.bloodType = ""
        }
        patientsVO.bloodType = data?.get("bloodType") as String

        if (data.get("height") == null){
            patientsVO.height = ""
        }
        patientsVO.height = data?.get("height") as String

        if (data.get("address") == null){
            patientsVO.address = ""
        }
        patientsVO.address = data?.get("address") as String
        return patientsVO
    }
    return null
}

fun toConvertSpeciality(data: HashMap<String, String>?): SpecialitiesVO? {
    data?.let {
        val specialitiesVO = SpecialitiesVO()
        specialitiesVO.id = data?.get("id").toString()
        specialitiesVO.name = data?.get("name").toString()
        return specialitiesVO
    }
    return null
}
