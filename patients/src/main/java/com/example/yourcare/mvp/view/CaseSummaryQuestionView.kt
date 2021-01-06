package com.example.yourcare.mvp.view

import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.RelatedQuestionVO

interface CaseSummaryQuestionView :BaseView{
    fun replaceAnswerList(position : Int, caseSummaryVO: CaseSummaryVO)
    fun showSpecialQuestion(speicalQuestion : List<RelatedQuestionVO>)
    fun navigateToConfirmRequestScreen(patientId:Long,speciality:String)
}