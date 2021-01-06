package com.example.yourcare.mvp.view

import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ChatVO
import com.example.shared.data.vos.MedicineVO
import com.example.shared.data.vos.PatientsVO

interface ChatView:BaseView {
    fun showPatientData(patientsVO: PatientsVO)
    fun showCaseSummary(caseSummaryList: List<CaseSummaryVO>)
    fun displayChatText(chatText: List<ChatVO>)
    fun showPrescription(medicineList: List<MedicineVO>)

}