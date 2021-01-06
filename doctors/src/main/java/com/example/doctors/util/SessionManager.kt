package com.example.doctors.util

import android.content.Context
import android.content.SharedPreferences
import com.example.shared.util.*

object SessionManager {
    private const val NAME = sharePreferenceDoctor
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var doctor_name: String?
        get() = preferences.getString(sharePreferenceName, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceName, value)
        }

    var doctor_id: Long?
        get() = preferences.getLong(sharePreferenceID, 0)
        set(value) = preferences.edit {
            it.putLong(sharePreferenceID, value!!)
        }

    var doctor_speciality: String?
        get() = preferences.getString(sharePreferenceSpeciality, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceSpeciality, value)
        }

    var device_id: String?

        get() = preferences.getString(sharePreferenceRequestID, "request000")

        set(value) = preferences.edit {
            it.putString(sharePreferenceRequestID, value)
        }

    var pill_name:String?
    get()= preferences.getString(sharePreferencePillName,"")
    set(value) = preferences.edit{
        it.putString(sharePreferencePillName,value)
    }
}