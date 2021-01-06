package com.example.doctors.mvp.presenters.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.doctors.dummy.getCaseSummary
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import com.example.doctors.mvp.presenters.AbstractBasePresenter
import com.example.doctors.mvp.presenters.MainPresenter
import com.example.doctors.mvp.view.MainView
import com.example.shared.data.vos.*

class MainPresenterImpl:AbstractBasePresenter<MainView>(),MainPresenter {

    private var mYourcareModel: YourcareModel = YourcareModelImpl
    private val tag = "RegisterPresenter"
    override fun onUiReady(context: Context, owner: LifecycleOwner) {
//       loadDoctors(owner)
//       loadAllSpecialities(owner)
        //persistance
        loadConsultationRequest(owner)

    }

    override fun onTapAcceptRequest(consultationId: Long) {
        mView?.navigateToStartConsultationScreen(consultationId)
    }

    override fun loadConsultationRequestWithSpeciality(speciality: String) {
        mYourcareModel.getConsultationRequestWithSpeciality(speciality,onSuccess = {
            mView?.showConsultationRequest(it)
        },onFailure = {
            mView?.showError(it)
        })
    }

    override fun loadConsultationRequest(owner: LifecycleOwner) {
        mYourcareModel.getConsultationRequest {
            Log.d(tag,"Success Consultation Request"+it)
        }.observe(owner, Observer {
            Log.d(tag,"Load Consultation Request"+it)
        })
    }


//    override fun loadDoctors(lifecycleOwner: LifecycleOwner) {
//        mYourcareModel.getDoctors {
//            mView?.showError(it)
//        }.observe(lifecycleOwner, Observer {
//            var docList:List<DoctorsVO> = it
//            Log.d(tag,"Load Doctors"+it)
//        })
//    }
//
//    override fun loadDoctorsWithSpecialities(speciality:String) {
//        mYourcareModel.getDoctorWithSpecialities(speciality = speciality,onSuccess = {
//            it.let {
//                Log.d(tag,"Doctors with specialities" + it)
//            }
//        },onFailure = {
//            Log.d(tag,"Failed Get Doctors with specialities" + it)
//        })
//    }
//
//    override fun loadGeneralQuestionTemplate(type: String) {
//        mYourcareModel.getGeneralQuestionTemplate(type,onSuccess = {
//            Log.d(tag,"General Question Template" + it)
//
//        },onFailure = {
//            Log.d(tag," Failed general question template" + it)
//        })
//    }
//

//
//    override fun startConsultationRequest(
//        date: String,
//        patientsVO: PatientsVO,
//        time: String,
//        caseSummaryVO: CaseSummaryVO
//    ) {
//        mYourcareModel.startConsultationRequest(date,time,patientsVO,caseSummaryVO)
//    }
//
//    override fun loadAllSpecialities(lifecycleOwner: LifecycleOwner) {
//        mYourcareModel.getAllSpeciality {
//            mView?.showError(it)
//        }.observe(lifecycleOwner, Observer {
//            Log.d(tag,"LOAD ALL SPECIALITIES" + it)
//        })
//    }
//
//
//
//    override fun checkout(
//        caseSummaryVO: List<CaseSummaryVO>,
//        medicineVO: MedicineVO,
//        patientsVO: PatientsVO,
//        id: Long,
//        deliveryRoutine: String,
//        address: String
//    ) {
//        mYourcareModel.checkout(caseSummaryVO,medicineVO,patientsVO,id,deliveryRoutine,address)
//    }




}