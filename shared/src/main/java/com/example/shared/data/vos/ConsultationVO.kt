package com.example.shared.data.vos

import com.google.gson.annotations.SerializedName

data class ConsultationVO(

        @SerializedName("id")
        var id: Long ?= 0,

        @SerializedName("chat")
        var chatVO: List<ChatVO> ?= arrayListOf(),

        @SerializedName("prescription")
        var medicineVO: List<MedicineVO> ?= arrayListOf(),

        @SerializedName("patients")
        var patients: List<PatientsVO> ?= arrayListOf(),

        @SerializedName("case_summary")
        var caseSummaryVO: List<CaseSummaryVO>? = arrayListOf(),

        @SerializedName("finish_consultation")
        var finishConsultation:Boolean ?= false,

        @SerializedName("doctors")
        var doctorsVO: DoctorsVO? = DoctorsVO()
)

fun MutableMap<String,Any>.convertToConsultationVO():ConsultationVO{
        var consultationVO = ConsultationVO()
        consultationVO.id = this?.get("id") as Long
       consultationVO.doctorsVO = toConvertDoctor((this?.get("doctors") as HashMap<String, String>?))
        return consultationVO
}

fun toConvertDoctor(data:HashMap<String, String>?): DoctorsVO? {
    data?.let {
        val doctorsVO = DoctorsVO()
        doctorsVO.id = data.get("id") as Long
        doctorsVO.name = data?.get("name").toString()
        doctorsVO.speciality = data?.get("speciality").toString()
        doctorsVO.deviceId = data?.get("device_id").toString()
        doctorsVO.doctorCasesummary = data?.get("doctor_casesummary").toString()
        doctorsVO.gender = data?.get("gender").toString()
        doctorsVO.experience = data.get("experience") as Long
        doctorsVO.degree = data?.get("degree").toString()
        doctorsVO.bio = data?.get("bio").toString()
        doctorsVO.phone = data?.get("phone").toString()
        doctorsVO.address = data?.get("address").toString()
        return doctorsVO
    }

    return null
}