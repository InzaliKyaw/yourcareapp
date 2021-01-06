package com.example.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Medicine")
data class MedicineVO(

    @SerializedName("id")
    @PrimaryKey
    var id:String = "",

    @SerializedName("pill_name")
    var pillName:String? = "",

    @SerializedName("speciality")
    var speciality:String? = "",

    @SerializedName("price")
    var price:Long? = 0,

    @SerializedName("routine")
    var routine:Routine? = Routine(),

    @SerializedName("days")
    var days:Long? = 0,

    @SerializedName("amount")
    var amount:Long? = 0,

    @SerializedName("remark")
    var remark:String? = ""
)