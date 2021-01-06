package com.example.yourcare.mvp.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.RelatedQuestionVO
import com.example.yourcare.delegates.SpecialQuestionDelegates
import com.example.yourcare.mvp.AbstractBasePresenter
import com.example.yourcare.mvp.CaseSummaryQuestionPresenter
import com.example.yourcare.mvp.view.CaseSummaryQuestionView

class CaseSummaryQuestionPresenterImpl:CaseSummaryQuestionPresenter,AbstractBasePresenter<CaseSummaryQuestionView>(){
    private var mYourcareModel:YourcareModel = YourcareModelImpl
    private var tag:String = "Case Summary Question"


    override fun onTapConfirmConsultation(patientId:Long,id: String, question: String,
                                          answer: String,patientSpeciality:String) {
        mYourcareModel.addPatientCaseSummary(patientId, id, question, answer)
        mView.navigateToConfirmRequestScreen(patientId,patientSpeciality)
    }

    override fun loadSpecialQuestions(lifecycleOwner: LifecycleOwner,speciality: String) {
        mYourcareModel.getRelatedQuestionWithSpecialities(speciality,onSuccess = {
            mView.showSpecialQuestion(it)
        },onFailure = {
            Log.d(tag,it)
        })
    }

    override fun onUiReady(context: Context, lifecycleOwner: LifecycleOwner) {

    }

    override fun onAnswerChange(position: Int, caseSummaryVO: CaseSummaryVO) {
        mView.replaceAnswerList(position, caseSummaryVO)
    }

}