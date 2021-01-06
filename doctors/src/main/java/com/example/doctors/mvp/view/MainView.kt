package com.example.doctors.mvp.view

import com.example.shared.data.vos.ConsultationRequestVO

interface MainView:BaseView{
//    fun callDoctors()
    fun showConsultationRequest(consultationRequestList: List<ConsultationRequestVO>)
    fun navigateToStartConsultationScreen(consultationId: Long)
}