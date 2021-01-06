package com.example.shared.data.vos

import com.google.gson.annotations.SerializedName

data class ChatVO (
        @SerializedName("")
        var id:String ?="",

        @SerializedName("")
        var messageImage:String? = "",

        @SerializedName("")
        var messageText:String? = "",

        @SerializedName("")
        var sendAt:String? = "",

        @SerializedName("sent_by")
        var sendBy:String? = ""
)