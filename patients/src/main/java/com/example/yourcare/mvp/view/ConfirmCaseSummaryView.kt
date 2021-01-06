package com.example.yourcare.mvp.view

import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.PatientsVO

interface ConfirmCaseSummaryView:BaseView {
    fun navigateToStartConsultation(patientId:Long,consultationRequestId:Long)
    fun showCaseSummary(caseSummaryList: List<CaseSummaryVO>)
    fun showPatientData(patientsVO: PatientsVO)
}