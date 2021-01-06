package com.example.shared.data.vos

import com.google.gson.annotations.SerializedName

data class CaseSummaryVO(

        @SerializedName("id")
        var id:String ?= "",

        @SerializedName("question")
        var question: String = "",

        @SerializedName("answer")
        var answer:String = ""
)