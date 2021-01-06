package com.example.doctors.mvp.view

import com.example.shared.data.vos.GeneralQuestionTemplateVO

interface GeneralQuestionView:BaseView {
    fun showGeneralQuestionTemplate(gQTlist:List<GeneralQuestionTemplateVO>)
    fun sendSelectedQuestion(quesion:String)
}