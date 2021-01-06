package com.example.yourcare.util

import android.content.Context
import android.content.SharedPreferences
import com.example.shared.data.vos.SpecialitiesVO
import com.example.shared.util.*
import com.google.gson.Gson

object SessionManager {
    private const val NAME = sharePreferencePatient
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor    = edit()
        operation(editor)
        editor.apply()
    }

    var patient_id:Long?
    get() = preferences.getLong(sharePreferenceID,0)
    set(value) = preferences.edit(){
        it.putLong(sharePreferenceID,value!!)
    }
    var patient_name:String?
    get() = preferences.getString(sharePreferencePatientName,"")
    set(value) = preferences.edit(){
        it.putString(sharePreferencePatientName,value)
    }
    var patient_email:String?
        get() = preferences.getString(sharePreferencePatientEmail,"")
        set(value) = preferences.edit(){
            it.putString(sharePreferencePatientEmail,value)
        }

    var patient_deviceId:String?
    get() = preferences.getString(sharePreferencePatientDeviceID,"")
    set(value) = preferences.edit(){
        it.putString(sharePreferencePatientDeviceID,value)
    }

    var patient_birthdate:String?
    get() = preferences.getString(sharePreferencePatientBirthdate,"")
    set(value) = preferences.edit(){
        it.putString(sharePreferencePatientBirthdate,value)
    }

    var patient_bloodpressure:String?
    get() = preferences.getString(sharePreferencePatientBloodPressure,"")
    set(value) = preferences.edit(){
        it.putString(sharePreferencePatientBloodPressure,value)
    }

    var patient_weight:String?
    get() = preferences.getString(sharePreferencePatientWeight,"")
    set(value) = preferences.edit(){
        it.putString(sharePreferencePatientWeight,value)
    }

    var patient_allergymedicine:String?
    get() = preferences.getString(sharePreferencePatientAllergyMedicine,"")
    set(value) = preferences.edit(){
        it.putString(sharePreferencePatientWeight,value)
    }
    var patient_height:String?
        get() = preferences.getString(sharePreferencePatientHeight,"")
        set(value) = preferences.edit(){
            it.putString(sharePreferencePatientHeight,value)
        }
    var patient_bloodtype:String?
        get() = preferences.getString(sharePreferencePatientHeight,"")
        set(value) = preferences.edit(){
            it.putString(sharePreferencePatientHeight,value)
        }


    var patient_address:String?
    get() = preferences.getString(sharePreferencePatientAddress,"")
    set(value) = preferences.edit(){
        it.putString(sharePreferencePatientAddress,value)
    }


}