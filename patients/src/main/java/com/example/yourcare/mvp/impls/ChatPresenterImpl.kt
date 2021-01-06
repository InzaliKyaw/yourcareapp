package com.example.yourcare.mvp.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import com.example.shared.data.vos.ChatVO
import com.example.yourcare.mvp.AbstractBasePresenter
import com.example.yourcare.mvp.ChatPresenter
import com.example.yourcare.mvp.view.ChatView
import com.example.yourcare.util.SessionManager
import java.text.SimpleDateFormat
import java.util.*

class ChatPresenterImpl:ChatPresenter,AbstractBasePresenter<ChatView> (){
    private var mYourcareModel: YourcareModel = YourcareModelImpl
    val TAG= "Chat Presenter Impl"
    override fun loadPatientData(lifecycleOwner: LifecycleOwner,patientId: Long) {
        mYourcareModel.getPatientById(patientId).observe(lifecycleOwner, Observer {
            it.forEach {
                mView.showPatientData(it)
            }
        })
    }

    override fun loadPatientCaseSummary(lifecycleOwner: LifecycleOwner, patientId: Long) {
        mYourcareModel.getPatientCaseSummary(patientId,onSuccess = {
            mView.showCaseSummary(it)
        },onFailure = {
            mView.showError(it)
        })
    }
    override fun loadChatMessages(chatId: Long) {
        mYourcareModel.getChatConsultation(chatId = chatId,
            onSuccess = {
                mView.displayChatText(it)
            }, onFailure = {
                Log.d(TAG, "Chat List Failed" + it)
            }
        )
    }

    override fun loadPrescriptionFromFirebase(chatId: Long) {
        mYourcareModel.getPrescriptionFromConsultation(chatId,onSuccess = {
                mView.showPrescription(it)
        },onFailure = {
            Log.d(TAG, "Load Prescription Failed" + it)
        })
    }

    override fun loadAllChatMessage(chatList: List<ChatVO>) {

    }

    override fun addChatText(chatText: String, consultationId: Long) {
        var currentTime: Date = Calendar.getInstance().time
        var dateFormatter: SimpleDateFormat = SimpleDateFormat("hh:mm a")
        var sendAt: String = dateFormatter.format(currentTime).toString()
        var sendBy:String = SessionManager.patient_name.toString()
        mYourcareModel.sendMessage(consultationId, "", "", chatText, sendAt, sendBy)
        loadChatMessages(consultationId)
    }

    override fun onUiReady(context: Context, lifecycleOwner: LifecycleOwner) {
    }
}