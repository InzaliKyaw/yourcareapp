package com.example.shared.data.vos

import com.google.gson.annotations.SerializedName

data class Routine (
        @SerializedName("morning")
        var morning:Boolean? = false,

        @SerializedName("noon")
        var noon:Boolean? = false,

        @SerializedName("night")
        var night:Boolean? = false,

        @SerializedName("morning_times")
        var morningTimes: Long? = 0,

        @SerializedName("noon_times")
        var noonTimes:Long? = 0,

        @SerializedName("night_times")
        var nightTimes:Long? = 0
)