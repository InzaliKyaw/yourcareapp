package com.example.doctors.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.doctors.mvp.view.ChatView

interface ChatPresenter:BasePresenter<ChatView> {
    fun onTapGeneralQuestionFAB()
    fun onTapPrescription(speciality: String)
    fun onTapMedicalRecord()
    //same Id with consultation and consultation Request
    fun addChatText(chatText: String,consultationId: Long)
    fun loadChatMessages(chatId: Long)
    fun loadConsultationRequestWithId(lifecycleOwner: LifecycleOwner, consultationId: Long)
    fun loadCaseSummaryQAndA(consultationId: Long)
}