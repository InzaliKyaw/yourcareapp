package com.example.yourcare.mvp

import androidx.lifecycle.LifecycleOwner
import com.example.yourcare.mvp.view.CaseSummaryQuestionView
import com.example.yourcare.mvp.view.ConfirmCaseSummaryView

interface ConfirmCaseSummaryPresenter:BasePresenter<ConfirmCaseSummaryView> {
    fun loadPatientGeneralData(lifecycleOwner: LifecycleOwner,patientId:Long)
    fun loadPatientCaseSummary(lifecycleOwner: LifecycleOwner,patientId: Long)
    fun startConsultationRequest(lifecycleOwner: LifecycleOwner,patientId: Long,speciality:String)
}