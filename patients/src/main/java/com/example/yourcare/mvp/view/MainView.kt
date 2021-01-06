package com.example.yourcare.mvp.view

import com.example.shared.data.vos.DoctorsVO
import com.example.shared.data.vos.SpecialitiesVO

interface MainView:BaseView {
//    fun navigateToConsultationRequest()
    fun showAllSpecialities(specialities:List<SpecialitiesVO>)
    fun displayConfirmDialog(speciality:String)
    fun showAvailableDoctor(doctorsVO: DoctorsVO)
}