package com.example.shared.data.vos

import com.google.gson.annotations.SerializedName

data class RelatedQuestionVO(

    @SerializedName("id")
    var id:String = "",

    @SerializedName("question")
    var question:String = "",
)