package com.example.doctors.mvp.presenters.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.example.doctors.mvp.presenters.AbstractBasePresenter
import com.example.doctors.mvp.presenters.ChatPresenter
import com.example.doctors.mvp.view.ChatView
import com.example.doctors.util.SessionManager
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import java.text.SimpleDateFormat
import java.util.*

class ChatPresenterImpl:ChatPresenter,AbstractBasePresenter<ChatView>() {

    private var mYourcareModel: YourcareModel = YourcareModelImpl
    private var TAG = "Chat Presenter"
    override fun onTapGeneralQuestionFAB() {
        mView.navigateToGeneralQuestionTemplate()
    }

    override fun onTapPrescription(speciality: String) {
        mView.navigateToPrescription(speciality)
    }

    override fun onTapMedicalRecord() {
        mView.navigateToMedicalRecordScreen()
    }

    override fun addChatText(chatText: String,consultationId: Long) {

        var currentTime: Date = Calendar.getInstance().time
        var dateFormatter: SimpleDateFormat = SimpleDateFormat("hh:mm a")
        var sendAt: String = dateFormatter.format(currentTime).toString()
        //NeedToFixWithGetCurrentUser
       // var sendByVO = SendByVO("Aung Aung")
        //RandomID
        var sendBy:String = SessionManager.doctor_name.toString()
        mYourcareModel.sendMessage(consultationId, "", "", chatText, sendAt, sendBy)
        loadChatMessages(consultationId)
//        mYourcareModel.getConsultations(onSuccess = {
//            var consulationCount = it.size.toLong()
//            Log.d(TAG, "CONSULTATION" + consulationCount)
//
//        }, onFailure = {
//            mView.showError(it)
//        })


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

    override fun loadConsultationRequestWithId(
        lifecycleOwner: LifecycleOwner,
        consultationId: Long
    ) {
        mYourcareModel.getConsultationRequestWithId(consultationId).observe(lifecycleOwner,
            androidx.lifecycle.Observer {
                it.forEach {
                    mView.showConsultationRequestData(it)
                    mView.getConsultationSpeciality(it.specialitiesVO?.name.toString())
                }
            })

    }

    override fun loadCaseSummaryQAndA(consultationId: Long) {
        mYourcareModel.getConsultationRequestCaseSummaryQAndA(consultationId, onSuccess = {
            mView?.showCaseSummaryQAndA(it)
        }, onFailure = {
            Log.d(TAG, "Prescription" + it)
        })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }


}