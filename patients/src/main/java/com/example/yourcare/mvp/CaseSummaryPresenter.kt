package com.example.yourcare.mvp

import androidx.lifecycle.LifecycleOwner
import com.example.yourcare.mvp.view.CaseSummaryView

interface CaseSummaryPresenter{
    fun onTapContinue(lifecycleOwner: LifecycleOwner,
                   speciality:String,
                   id:Long,
                   birthdate:String,
                   height:String,
                   bloodType:String,
                   bloodPressure:String,
                   weight:String,
                   allergicMedicine:String,
                   address: String)

//    fun addSpecialityToPatient(
//        lifecycleOwner: LifecycleOwner,
//        speciality: String
//    )

}