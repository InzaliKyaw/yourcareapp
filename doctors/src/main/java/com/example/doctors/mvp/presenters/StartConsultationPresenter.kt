package com.example.doctors.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.doctors.mvp.view.StartConsultationView

interface StartConsultationPresenter:BasePresenter<StartConsultationView> {
    fun onTapStartConsultation()
//    fun loadConsultationRequestWithSpeciality(speciality:String)
    fun loadConsultationRequestWithId(lifecycleOwner: LifecycleOwner,consultationId:Long)
    fun loadCaseSummaryQAndA(consultationId: Long)
}