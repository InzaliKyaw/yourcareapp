package com.example.doctors.mvp.view

import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ConsultationRequestVO

interface StartConsultationView:BaseView {
    fun navigateToChatScreen()
    fun showConsultationRequestData(consultationRequestVO: ConsultationRequestVO)
    fun showCaseSummaryQAndA(caseSummaryList:List<CaseSummaryVO>)
}