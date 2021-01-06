package com.example.yourcare.mvp

import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.RelatedQuestionVO
import com.example.yourcare.delegates.SpecialQuestionDelegates
import com.example.yourcare.mvp.view.CaseSummaryQuestionView

interface CaseSummaryQuestionPresenter:BasePresenter<CaseSummaryQuestionView>,SpecialQuestionDelegates {

    fun onTapConfirmConsultation(patientId:Long,id: String,
                                 question: String,
                                 answer: String,
                                patientSpeciality: String)
    fun loadSpecialQuestions(lifecycleOwner: LifecycleOwner,speciality:String)

}