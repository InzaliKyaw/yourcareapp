package com.example.doctors.mvp.presenters.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.doctors.mvp.presenters.AbstractBasePresenter
import com.example.doctors.mvp.presenters.StartConsultationPresenter
import com.example.doctors.mvp.view.StartConsultationView
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import com.example.shared.data.vos.ConsultationRequestVO

class StartConsultationPresenterImpl : StartConsultationPresenter,
    AbstractBasePresenter<StartConsultationView>() {
    private var mYourcareModel: YourcareModel = YourcareModelImpl
    private var TAG = "START CONSULTATION"
    override fun onTapStartConsultation() {
        mView?.navigateToChatScreen()
    }

    override fun loadConsultationRequestWithId(lifecycleOwner: LifecycleOwner,consultationId: Long) {
        mYourcareModel.getConsultationRequestWithId(consultationId).observe(
            lifecycleOwner, Observer {
                it.forEach {
                mView?.showConsultationRequestData(it)
            }
            }
        )

    }

    override fun loadCaseSummaryQAndA(consultationId: Long) {
        mYourcareModel.getConsultationRequestCaseSummaryQAndA(consultationId,onSuccess = {
            mView?.showCaseSummaryQAndA(it)
        },onFailure = {
            Log.d(TAG,"Presenter Impl"+it)
        })
    }

//    override fun loadConsultationRequestWithSpeciality(speciality: String) {
//        mYourcareModel.getConsultationRequestWithSpeciality(speciality, onSuccess = {
//            it.forEach {
//                mView?.showConsultationRequestData(it)
//            }
//        }, onFailure = {
//            mView?.showError(it)
//        })
//    }


    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

}