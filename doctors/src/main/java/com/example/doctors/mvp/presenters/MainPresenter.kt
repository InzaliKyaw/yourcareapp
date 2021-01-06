package com.example.doctors.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.doctors.delegates.RequestDelegates
import com.example.doctors.mvp.presenters.BasePresenter
import com.example.doctors.mvp.view.BaseView
import com.example.doctors.mvp.view.MainView
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.MedicineVO
import com.example.shared.data.vos.PatientsVO

interface MainPresenter:BasePresenter<MainView>,RequestDelegates {

    fun loadConsultationRequestWithSpeciality(speciality:String)

    //Persistance
    fun loadConsultationRequest(lifecycleOwner: LifecycleOwner)

//    fun loadDoctors(lifecycleOwner: LifecycleOwner)
//    fun loadDoctorsWithSpecialities(speciality:String)
//    fun loadGeneralQuestionTemplate(type:String)
//    fun startConsultationRequest(date:String,patientsVO: PatientsVO,time:String,caseSummaryVO: CaseSummaryVO)
//    fun loadAllSpecialities(lifecycleOwner: LifecycleOwner)
//    fun checkout(caseSummaryVO: List<CaseSummaryVO>, medicineVO: MedicineVO, patientsVO: PatientsVO, id:Long, deliveryRoutine: String, address:String)


}