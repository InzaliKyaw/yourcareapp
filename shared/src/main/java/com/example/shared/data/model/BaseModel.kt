package com.example.shared.data.model

import android.content.Context
import com.example.shared.data.vos.DoctorsVO
import com.example.shared.persistence.db.DoctorDB

abstract class BaseModel {
    protected lateinit var mTheDB: DoctorDB

    init {

    }
    fun initDatabase(context: Context){
        mTheDB = DoctorDB.getDBInstance(context)
    }

}