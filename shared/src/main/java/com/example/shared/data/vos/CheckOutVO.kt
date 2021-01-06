package com.example.shared.data.vos

import com.google.gson.annotations.SerializedName

data class CheckOutVO(

        @SerializedName("id")
        var id:Long = 0,

        @SerializedName("patients")
        var patientsVO: PatientsVO = PatientsVO(),

        @SerializedName("prescription")
        var prescriptionVO: MedicineVO = MedicineVO(),

        @SerializedName("case_summary")
        var caseSummaryVO: List<CaseSummaryVO> = arrayListOf(),

        @SerializedName("address")
        var address:String = "",

        @SerializedName("delivery_routine")
        var deliveryRoutine:String = ""


)