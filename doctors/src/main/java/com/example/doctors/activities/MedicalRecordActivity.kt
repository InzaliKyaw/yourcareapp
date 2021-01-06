package com.example.doctors.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.doctors.R

class MedicalRecordActivity:BaseActivity() {
    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,MedicalRecordActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_record)
    }
    override fun showError(error: String) {

    }
}