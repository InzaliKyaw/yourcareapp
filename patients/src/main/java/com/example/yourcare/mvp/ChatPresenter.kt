package com.example.yourcare.mvp

import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.vos.ChatVO
import com.example.yourcare.mvp.view.ChatView

interface ChatPresenter:BasePresenter<ChatView> {
    fun loadPatientData(lifecycleOwner: LifecycleOwner,patientId:Long)
    fun loadPatientCaseSummary(lifecycleOwner: LifecycleOwner,patientId: Long)
    fun loadAllChatMessage(chatList:List<ChatVO>)
    fun addChatText(chatText: String,consultationId: Long)
    fun loadChatMessages(chatId: Long)
    fun loadPrescriptionFromFirebase(chatId: Long)
}