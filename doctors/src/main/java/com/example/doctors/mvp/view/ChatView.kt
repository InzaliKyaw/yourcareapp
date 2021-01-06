package com.example.doctors.mvp.view

import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ChatVO
import com.example.shared.data.vos.ConsultationRequestVO

interface ChatView:BaseView {
    fun navigateToGeneralQuestionTemplate()
    fun navigateToPrescription(speciality: String)
    fun navigateToMedicalRecordScreen()
    fun displayChatText(chatText:List<ChatVO>)
    fun showConsultationRequestData(consultationRequestVO: ConsultationRequestVO)
    fun showCaseSummaryQAndA(caseSummaryList: List<CaseSummaryVO>)
    fun getConsultationSpeciality(speciality: String)
}